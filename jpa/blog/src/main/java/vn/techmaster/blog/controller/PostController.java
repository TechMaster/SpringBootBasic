package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.service.IAuthenService;

@Controller
public class PostController {
  @Autowired
  private IAuthenService authenService;

  @GetMapping("/login")
  public String showLoginForm(Model model) {
    model.addAttribute("loginRequest", new LoginRequest());
    return "login.html";
  }

  @PostMapping("/login")
  public String handleLogin(@ModelAttribute LoginRequest loginRequest, 
    BindingResult bindingResult, 
    Model model){
      return "";

  }
}
