package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.blog.DTO.PostMapper;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;

@Service
public class PostService implements IPostService {
  @Autowired
  PostRepository postRepo;

  @Autowired
  UserRepository userRepo;

  @Override
  public List<Post> findAll() {
    return postRepo.findAll();
  }

  @Override
  public List<Post> getAllPostOfUser(User user) {
    return postRepo.findAll();
  }

  public void createNewPost(PostRequest postRequest) {
    Optional<User> optionalUser = userRepo.findById(postRequest.getUser_id());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      Post post = PostMapper.INSTANCE.postRequestToPost(postRequest);
      user.addPost(post);
      userRepo.flush();
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
  public void updatePost(PostRequest postRequest) {
    Optional<Post> optionalPost = postRepo.findById(postRequest.getId());
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      post.setTitle(postRequest.getTitle());
      post.setContent(postRequest.getContent());
      postRepo.saveAndFlush(post);
    } else {
      createNewPost(postRequest);
    }
    
  }
}
