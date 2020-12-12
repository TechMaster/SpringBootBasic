package vn.techmaster.demobean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demobean.bean.Car;

@Controller
public class HomeController {

  @Autowired
  Car mycar; //Lấy bean có tên là car

  @ResponseBody
  @GetMapping(value = "/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return mycar.toString();
  }
}
