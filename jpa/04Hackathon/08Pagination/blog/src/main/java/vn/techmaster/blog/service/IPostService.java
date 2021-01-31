package vn.techmaster.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.techmaster.blog.controller.request.CommentRequest;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.Tag;
import vn.techmaster.blog.model.User;

public interface IPostService {
  public List<Post> findAll();
  public Page<Post> findAllPaging(int page, int pageSize);

  public List<Post> getAllPostOfUser(User user);  
  public List<Post> getAllPostsByUserID(long user_id);
  public void createNewPost(PostRequest postRequest) throws PostException;
  public Optional<Post> findById(Long id);
  public void deletePostById(Long id);
  public void updatePost(PostRequest postRequest) throws PostException;
  public void addComment(CommentRequest commentRequest, long user_id) throws PostException;

  public List<Tag> getAllTags();

  public List<Post> searchPost(String term, int limit, int offset);

  public void reindexFullText();

  public void generateSampleData();
}