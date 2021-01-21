package vn.techmaster.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.iPostService;



@Controller
public class PostController {
  @Autowired private IAuthenService authenService;
  @Autowired private iPostService postService;

  @GetMapping("/posts")
  public String getAllPosts(Model model, HttpServletRequest request) { 
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null) {
      model.addAttribute("user", user);
      List<Post> posts = postService.getAllPostsByUserID(user.getId());
      model.addAttribute("posts", posts);
      return Route.ALLPOSTS;
    } else {
      return Route.REDIRECT_HOME;
    } 
  }

  @GetMapping("/post")
  public String createEditPostForm(Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null) {
      PostRequest postReqest = new PostRequest();
      postReqest.setUser_id(user.getId());
      model.addAttribute("post", postReqest);
      model.addAttribute("user_fullname", user.getFullname());
      return Route.POST;
    } else {
      return Route.REDIRECT_HOME;
    }
  }

  @PostMapping("/post")
  public String createEditPostSubmit(@ModelAttribute PostRequest postRequest, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null && user.getId() == postRequest.getUser_id()) {
      postService.createNewPost(postRequest);
      return Route.REDIRECT_POSTS;
    } else {
      return Route.REDIRECT_HOME;
    }

  }
}
