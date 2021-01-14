package vn.techmaster.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import vn.techmaster.blog.security.CookieManager;



@Controller
public class PostController {
  @Autowired private CookieManager cookieManager;

  @GetMapping("/posts")
  public String getAllPosts(HttpServletRequest request) {
    String loginEmail = cookieManager.getAuthenticatedEmail(request);
    if (loginEmail != null) {
      return Route.ALLPOSTS;
    } else {
      return Route.REDIRECT_HOME;
    }
    
  }
}
