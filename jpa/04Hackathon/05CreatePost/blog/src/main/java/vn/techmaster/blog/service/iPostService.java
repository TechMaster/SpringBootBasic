package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

public interface iPostService {
  List<Post> getAllPostOfUser(User user);  
  List<Post> getAllPostsByUserID(long user_id);
  public void createNewPost(PostRequest postRequest);
  Optional<Post> findById(Long id);
  void deletePostById(Long id);
  public void updatePost(PostRequest postRequest);
}