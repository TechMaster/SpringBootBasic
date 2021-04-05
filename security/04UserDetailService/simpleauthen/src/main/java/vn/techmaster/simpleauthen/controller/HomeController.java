package vn.techmaster.simpleauthen.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
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

    for (var authority:authorities) {
      System.out.println(authority.getAuthority());
    }
    
    model.addAttribute("login_name", loginName);
    model.addAttribute("authorities", authorities);
    return "index";
  }
}
