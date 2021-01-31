package vn.techmaster.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.service.AuthenException;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;
@Controller
public class HomeController {
  @Autowired private IAuthenService authenService;
  @Autowired private IPostService postService;
  
  public static final String LOGIN_REQUEST = "loginRequest";
  

  @GetMapping(value = {"/", "/{page}"})
  public String home(@PathVariable(value="page", required = false) Integer page, Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);    
    if (user != null) {  //Người dùng đã login      
      model.addAttribute("user", user);
    }
    if (page == null) {
      page = 0;
    }
    Page<Post> pagePosts = postService.findAllPaging(page, 8);

    List<Post> posts = pagePosts.getContent();
    model.addAttribute("posts", posts);
    List<Paging> pagings = Paging.generatePages(page, pagePosts.getTotalPages());
    model.addAttribute("pagings", pagings);
    return Route.HOME;
  }

  @GetMapping("/login")
  public String showLoginForm(Model model, HttpServletRequest request) {
    if (authenService.isLogined(request)) {
      return Route.REDIRECT_POSTS;
    }
    model.addAttribute(LOGIN_REQUEST, new LoginRequest());
    return Route.LOGIN_TEMPLATE;
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response) {
    authenService.clearLoginedCookie(response);
    return Route.REDIRECT_HOME;
  }


  @PostMapping("/login")
  public String handleLogin(@ModelAttribute LoginRequest loginRequest, 
    BindingResult bindingResult, 
    Model model,
    HttpServletResponse response){      
      if (!bindingResult.hasErrors()) {        
        try {
          UserInfo user = authenService.login(loginRequest);          
          authenService.setLoginedCookie(response, user);
          return Route.REDIRECT_POSTS;
        } catch (AuthenException e) {
          model.addAttribute(LOGIN_REQUEST, new LoginRequest(loginRequest.getEmail(), ""));
          model.addAttribute("errorMessage", e.getMessage());
          return Route.LOGIN_TEMPLATE;
        }
      } else {
        model.addAttribute(LOGIN_REQUEST, new LoginRequest());
        model.addAttribute("errorMessage", "Submitted is invalid");
        return Route.LOGIN_TEMPLATE;
      }
  }
}
