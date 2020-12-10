package vn.techmaster.bookstore.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  @ResponseBody //trả về dữ liệu trong trường body
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return "This is Amazon Book Store";
  }

  @ResponseBody
  @GetMapping(value="/about", produces=MediaType.TEXT_HTML_VALUE)
  public String getAbout() {
    return "About page";
  }
}
