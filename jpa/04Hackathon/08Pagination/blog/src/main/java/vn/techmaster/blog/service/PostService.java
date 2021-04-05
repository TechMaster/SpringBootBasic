package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.CacheMode;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.techmaster.blog.DTO.PostMapper;
import vn.techmaster.blog.controller.request.CommentRequest;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.Tag;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.TagRepository;
import vn.techmaster.blog.repository.UserRepository;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@Service
public class PostService implements IPostService {
  @Autowired
  PostRepository postRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  TagRepository tagRepo;

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Post> findAll() {
    return postRepo.findAll(); // Bổ xung pagination vào đây !
  }

  @Override
  public Page<Post> findAllPaging(int page, int pageSize) {
    return postRepo.findAll(PageRequest.of(page, pageSize)); // Bổ xung pagination vào đây !
  }

  @Override
  public List<Post> getAllPostOfUser(User user) {
    return postRepo.findAll();
  }

  public void createNewPost(PostRequest postRequest) throws PostException {
    Optional<User> optionalUser = userRepo.findById(postRequest.getUser_id());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      Post post = PostMapper.INSTANCE.postRequestToPost(postRequest);
      user.addPost(post);
      userRepo.flush();
    } else {
      throw new PostException("Cannot add post");
    }
  }

  @Override
  public List<Post> getAllPostsByUserID(long userId) {
    return postRepo.findByUserId(userId);
  }

  @Override
  public Optional<Post> findById(Long id) {
    return postRepo.findById(id);
  }

  @Override
  public void deletePostById(Long id) {
    postRepo.deleteById(id);
  }

  @Override
  public void updatePost(PostRequest postRequest) throws PostException {
    Optional<Post> optionalPost = postRepo.findById(postRequest.getId());
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      post.setTitle(postRequest.getTitle());
      post.setContent(postRequest.getContent());
      post.setTags(postRequest.getTags());
      postRepo.saveAndFlush(post);
    } else {
      createNewPost(postRequest);
    }

  }

  @Override
  public void addComment(CommentRequest commentRequest, long userId) throws PostException {
    Optional<Post> oPost = postRepo.findById(commentRequest.getPost_id());
    Optional<User> oUser = userRepo.findById(userId);
    if (oPost.isPresent() && oUser.isPresent()) {
      Post post = oPost.get();
      Comment comment = new Comment(commentRequest.getContent());
      comment.setUser(oUser.get());
      post.addComment(comment);
      postRepo.flush();
    } else {
      throw new PostException("Post or User is missing");
    }
  }

  @Override
  public List<Tag> getAllTags() {
    return tagRepo.findAll();
  }

  @Override
  public List<Post> searchPost(String terms, int limit, int offset) {
    return Search.session(em).search(Post.class).where(f -> f.match().fields("title", "content").matching(terms))
        .fetchHits(offset, limit);
  }
  
  @Override
  public void reindexFullText() {
    SearchSession searchSession = Search.session(em);

    MassIndexer indexer = searchSession.massIndexer(Post.class).dropAndCreateSchemaOnStart(true)
    .typesToIndexInParallel( 2 )
    .batchSizeToLoadObjects(10)
    .idFetchSize(200)
    .threadsToLoadObjects(5)
    .cacheMode(CacheMode.IGNORE);
    indexer.start();    
  }

  @Override
  @Transactional
  public void generateSampleData() {
    List<User> users = userRepo.findAll();
    List<Tag> tags = tagRepo.findAll();

    int numberOfTags = tags.size();
    int maxTagsPerPost = numberOfTags / 3;

    Lorem lorem = LoremIpsum.getInstance();
    
    Random random = new Random();
    int numberOfUsers = users.size();
   for (int k = 0; k < 200; k++) {
      User user = users.get(random.nextInt(numberOfUsers));
      Post post = new Post(lorem.getTitle(2, 5), lorem.getParagraphs(2, 4));
      
      int numberOfComments = random.nextInt(numberOfUsers/2);
      for (int j = 0; j < numberOfComments; j++) {
        User commenter = users.get(random.nextInt(numberOfUsers));
        Comment comment = new Comment(lorem.getParagraphs(1, 1));
        comment.setUser(commenter);
        post.addComment(comment);
      }

      int numberTagsForPost = Math.max(1, random.nextInt(maxTagsPerPost));
      for (int i = 0; i < numberTagsForPost; i++) {
        post.addTag(tags.get(random.nextInt(numberOfTags)));
      }
     
      user.addPost(post);
      postRepo.save(post);
    }
    userRepo.flush();
  }
}