package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

public interface IPostService {
  public List<Post> findAll();
  public List<Post> getAllPostOfUser(User user);  
  public List<Post> getAllPostsByUserID(long user_id);
  public void createNewPost(PostRequest postRequest);
  public Optional<Post> findById(Long id);
  public void deletePostById(Long id);
  public void updatePost(PostRequest postRequest);
}