package vn.techmaster.blog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.DTO.PostMapper;
import vn.techmaster.blog.DTO.PostPOJO;
import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.DTO.UserMapper;
import vn.techmaster.blog.controller.request.IdRequest;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;



@Controller
public class PostController {
  @Autowired private IAuthenService authenService;
  @Autowired private IPostService postService;

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
      if (postRequest.getId() == null) {
        postService.createNewPost(postRequest); //Create
      } else {
        postService.updatePost(postRequest);  //Edit
      }
      
      return Route.REDIRECT_POSTS;
    } else {
      return Route.REDIRECT_HOME;
    }
  }

  //Lấy ra một post cụ thể cùng
  @GetMapping("/post/{id}")
  public String showPostAndComment(@PathVariable("id") long id, Model model, HttpServletRequest request) {
    UserInfo loginUserInfo = authenService.getLoginedUser(request);
    Optional<Post> optionalPost = postService.findById(id);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      PostPOJO postPOJO = PostMapper.INSTANCE.postToPostPOJO(post);
      model.addAttribute("post", postPOJO);
      model.addAttribute("user", loginUserInfo);
      return Route.POST_COMMENT;
    } else {
      return Route.REDIRECT_HOME;
    }    
  }

  //Xoá một post
  @PostMapping("/post/delete")
  public String deletePost(@ModelAttribute IdRequest idRequest, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null) {
      postService.deletePostById(idRequest.getId());      
    }
    return Route.REDIRECT_POSTS;
  }

  //Edit một post
  @PostMapping("/post/edit")
  public String editPost(@ModelAttribute IdRequest idRequest, Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);

    Optional<Post> optionalPost = postService.findById(idRequest.getId());

    if (user != null && optionalPost.isPresent()) {
      Post post = optionalPost.get();
      PostRequest postReqest = new PostRequest(
        post.getId(),
        post.getTitle(), 
        post.getContent(), 
        user.getId());
      
      model.addAttribute("post", postReqest);
      UserInfo userInfo = UserMapper.INSTANCE.userToUserInfo(post.getUser());
      model.addAttribute("user", userInfo);
      return Route.POST;
    } else {
      return Route.REDIRECT_POSTS;
    }   
  }
}