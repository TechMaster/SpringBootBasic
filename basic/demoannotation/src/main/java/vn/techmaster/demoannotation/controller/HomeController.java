package vn.techmaster.demoannotation.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demoannotation.annotation.Entity;
import vn.techmaster.demoannotation.model.Person;

@Controller
public class HomeController {

  @ResponseBody
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    String msg;
    try {
      Entity anno = Person.class.getAnnotation(Entity.class);
      msg = "Table name attribute: " + anno.name(); 
    } catch (Exception e) {
      msg = e.toString();
    }
    return msg;    
  }
}
