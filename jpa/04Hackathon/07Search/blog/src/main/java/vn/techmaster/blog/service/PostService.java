package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.CacheMode;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Post> getAllPostsByUserID(long user_id) {
    return postRepo.findByUserId(user_id);
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
  public void addComment(CommentRequest commentRequest, long user_id) throws PostException {
    Optional<Post> oPost = postRepo.findById(commentRequest.getPost_id());
    Optional<User> oUser = userRepo.findById(user_id);
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
}