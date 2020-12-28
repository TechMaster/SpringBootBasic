package vn.techmaster.bookstore.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class AboutController {
  @ResponseBody
  @GetMapping(value="/about", produces=MediaType.TEXT_HTML_VALUE)
  public String getAbout2() {
    return "About page";
  }
}
