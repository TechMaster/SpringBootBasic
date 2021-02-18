package vn.techmaster.simpleauthen.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  
  @GetMapping(value = "/", produces= MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public String getHome() {
    return "<h1>This is home page</h1>";
  }

  @GetMapping(value = "/about", produces= MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public String getAbout() {
    return "<h1>This is about</h1>";
  }

}
