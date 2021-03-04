package vn.techmaster.securityjpa.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  
  @GetMapping("/")
  public String showHomePage(Principal principal, Model model) {
    String loginName = (principal != null) ? principal.getName() : "";

    var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

    model.addAttribute("login_name", loginName);
    model.addAttribute("authorities", authorities);
    return "index";
  }

  @GetMapping("/free")
  public String showFree() {
    return "free";
  }

  @GetMapping("/admin")
  public String showAdmin() {
    return "admin";
  }

  @GetMapping("/user")
  public String showUserPage() {
    return "user";
  }

  @GetMapping("/author")
  public String showAuthorPage() {
    return "author";
  }

  @GetMapping("/editor")
  public String showEditorPage() {
    return "editor";
  }

}
