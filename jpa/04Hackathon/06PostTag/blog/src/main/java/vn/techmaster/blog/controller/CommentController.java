package vn.techmaster.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.controller.request.CommentRequest;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;
import vn.techmaster.blog.service.PostException;

@Controller
public class CommentController {
  @Autowired
  private IAuthenService authenService;
  @Autowired
  private IPostService postService;

  @PostMapping("/comment")
  public String handlePostComment(@ModelAttribute CommentRequest commentRequest, HttpServletRequest request) {
    UserInfo userLogin = authenService.getLoginedUser(request);
    if (userLogin != null) {
      try {
        postService.addComment(commentRequest, userLogin.getId());
      } catch (PostException e) {
        e.printStackTrace();
      }

      return "redirect:/post/" + commentRequest.getPost_id();

    } else {
      return Route.HOME;
    }
  }
  
}
