package vn.techmaster.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
  
  @GetMapping("/demo")
  public String showDemo(Model model) {
    return "demo.html";
  }
}
