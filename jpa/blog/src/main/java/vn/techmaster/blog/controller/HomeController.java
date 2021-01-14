package vn.techmaster.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.security.CookieManager;
import vn.techmaster.blog.service.AuthenException;
import vn.techmaster.blog.service.IAuthenService;
@Controller
public class HomeController {
  @Autowired private IAuthenService authenService;

  @Autowired private CookieManager cookieManager;

  static final String LOGIN_REQUEST = "loginRequest";
  static final String LOGIN_TEMPLATE = "login.html";  
  static final String REDIRECT_POSTS = "redirect:/posts";
  static final String REDIRECT_HOME = "redirect:/";

  @GetMapping("/")
  public String home() {
    return "home.html";
  }

  @GetMapping("/login")
  public String showLoginForm(Model model,  HttpServletRequest request) {
    if (cookieManager.isAuthenticated(request)) {
      return REDIRECT_POSTS;
    }
    model.addAttribute(LOGIN_REQUEST, new LoginRequest());
    return LOGIN_TEMPLATE;
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response) {
    cookieManager.setNotAuthenticated(response);
    return REDIRECT_HOME;
  }


  @PostMapping("/login")
  public String handleLogin(@ModelAttribute LoginRequest loginRequest, 
    BindingResult bindingResult, 
    Model model,
    HttpServletResponse response){      
      if (!bindingResult.hasErrors()) {        
        try {
          authenService.login(loginRequest);
          cookieManager.setAuthenticated(response, loginRequest.getEmail());          
          return REDIRECT_POSTS; 
        } catch (AuthenException e) {
          model.addAttribute(LOGIN_REQUEST, new LoginRequest(loginRequest.getEmail(), ""));
          model.addAttribute("errorMessage", e.getMessage());
          return LOGIN_TEMPLATE;
        }
      } else {
        model.addAttribute(LOGIN_REQUEST, new LoginRequest());
        model.addAttribute("errorMessage", "Submitted is invalid");
        return LOGIN_TEMPLATE;
      }
  }
}
