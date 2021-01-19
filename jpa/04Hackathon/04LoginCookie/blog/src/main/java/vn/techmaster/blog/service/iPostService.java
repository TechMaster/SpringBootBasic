package vn.techmaster.blog.service;

import java.util.List;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

public interface iPostService {
  List<Post> getAllPostOfUser(User user);
}
