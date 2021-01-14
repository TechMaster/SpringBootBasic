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
import vn.techmaster.blog.service.AuthenException;
import vn.techmaster.blog.service.IAuthenService;
@Controller
public class HomeController {
  @Autowired
  private IAuthenService authenService;
  final String LOGIN_REQUEST = "loginRequest";
  final String LOGIN_TEMPLATE = "login.html";
  final String LOGIN_COOKIE = "loginsuccess";
  final String REDIRECT_POSTS = "redirect:/posts";

  @GetMapping("/login")
  public String showLoginForm(Model model,  HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (var cookie:cookies) {
        if (cookie.getName().equals(LOGIN_COOKIE)) {
          return REDIRECT_POSTS;
        }
      }
    }


    model.addAttribute(LOGIN_REQUEST, new LoginRequest());
    return LOGIN_TEMPLATE;
  }

  @PostMapping("/login")
  public String handleLogin(@ModelAttribute LoginRequest loginRequest, 
    BindingResult bindingResult, 
    Model model,
    HttpServletResponse response){      
      if (!bindingResult.hasErrors()) {        
        try {
          authenService.login(loginRequest);
          Cookie loginCookie = new Cookie(LOGIN_COOKIE, loginRequest.getEmail());
          loginCookie.setMaxAge(30 * 60);
          response.addCookie(loginCookie);
          
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
