package vn.techmaster.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.service.IAuthenService;



@Controller
public class PostController {
  @Autowired private IAuthenService authenService;

  @GetMapping("/posts")
  public String getAllPosts(Model model, HttpServletRequest request) { 
    UserInfo user = authenService.getLoginedUser(request);
    if (user != null) {
      model.addAttribute("user", user);
      return Route.ALLPOSTS;
    } else {
      return Route.REDIRECT_HOME;
    }    
  }
}
