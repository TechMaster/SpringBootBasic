package vn.techmaster.securityjpa.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  
  @GetMapping("/")
  public String showHomePage(Principal principal) {
    if (principal == null) {
      return "index";
    } else {
      return "redirect:/login_success";
    }    
  }  

  @GetMapping("/member")
  public String showMember() {
    return "member";
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

  @GetMapping("/public")
  public String showPublic() {
    return "public";
  }
  

}
