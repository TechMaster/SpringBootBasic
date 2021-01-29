package vn.techmaster.blog.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.DTO.PostMapper;
import vn.techmaster.blog.DTO.PostPOJO;
import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.DTO.UserMapper;
import vn.techmaster.blog.controller.request.CommentRequest;
import vn.techmaster.blog.controller.request.IdRequest;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.Tag;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;
import vn.techmaster.blog.service.PostException;



@Controller
public class PostController {
  @Autowired private IAuthenService authenService;
  @Autowired private IPostService postService;

  @GetMapping("/posts")  //Liệt kê các post của một blogger cụ thể
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

  @GetMapping("/post")  //Show form để tạo mới Post
  public String createEditPostForm(Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null) {
      PostRequest postReqest = new PostRequest();
      postReqest.setUser_id(user.getId());
      model.addAttribute("post", postReqest);
      model.addAttribute("user", user);
      List<Tag> tags = postService.getAllTags();
      model.addAttribute("allTags", tags);
      return Route.POST;
    } else {
      return Route.REDIRECT_HOME;
    }
  }

  @PostMapping("/post")
  public String createEditPostSubmit(@Valid @ModelAttribute("post") PostRequest postRequest, BindingResult bindingResult, Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);
    if (bindingResult.hasErrors()) {
      List<Tag> tags = postService.getAllTags();
      model.addAttribute("tags", tags);
			return Route.POST;
    }  

    if (user != null && user.getId() == postRequest.getUser_id()) {
      try {
        if (postRequest.getId() == null) {
          postService.createNewPost(postRequest); //Create
        } else {
          postService.updatePost(postRequest);  //Edit
        }
      } catch (PostException pe) {
        return Route.REDIRECT_HOME;
      }
      
      return Route.REDIRECT_POSTS;
    } else {
      return Route.REDIRECT_HOME;
    }
  }

  //Lấy ra một post cụ thể cùng comment
  @GetMapping("/post/{id}")
  public String showPostAndComment(@PathVariable("id") long id, Model model, HttpServletRequest request) {    
    Optional<Post> optionalPost = postService.findById(id);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      PostPOJO postPOJO = PostMapper.INSTANCE.postToPostPOJO(post);
      model.addAttribute("post", postPOJO);

      Set<Tag> tags = post.getTags();
      model.addAttribute("tags", tags);

      List<Comment> comments = post.getComments(); 
      Collections.reverse(comments); 
      model.addAttribute("comments", comments); //Trả  về danh sách comment

      UserInfo user = authenService.getLoginedUser(request); //Login user

      if (user != null) { //Nếu user login và xem post, cần bổ xung chức năng comment
        model.addAttribute("user", user); //Người dùng đang login
        model.addAttribute("commentRequest", new CommentRequest(postPOJO.getId()));
      } else {
        model.addAttribute("commentRequest", new CommentRequest());
      }
      
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

  //Mở form để edit một post
  @PostMapping("/post/edit")
  public String editPost(@ModelAttribute IdRequest idRequest, Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);

    Optional<Post> optionalPost = postService.findById(idRequest.getId());

    if (user != null && optionalPost.isPresent()) {
      Post post = optionalPost.get();
      PostRequest postReqest =  PostMapper.INSTANCE.postToPostRequest(post);
      
      model.addAttribute("post", postReqest);
      List<Tag> tags = postService.getAllTags();
      model.addAttribute("allTags", tags);
      UserInfo userInfo = UserMapper.INSTANCE.userToUserInfo(post.getUser());
      model.addAttribute("user", userInfo);
      return Route.POST;
    } else {
      return Route.REDIRECT_POSTS;
    }   
  }
}