package vn.techmaster.bookstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String port;


  @ResponseBody
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return "Welcome " + appName + "<br> Serves at port:  " + port;
  }
}
