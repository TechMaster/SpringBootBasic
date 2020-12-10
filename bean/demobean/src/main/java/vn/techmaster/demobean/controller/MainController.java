package vn.techmaster.demobean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demobean.bean.Foo;
import vn.techmaster.demobean.bean.ObjSay;
import vn.techmaster.demobean.bean.Stone;

@Controller
public class MainController {

  @Autowired
  private Foo foo;

  @Autowired
  @Qualifier("bar") //Vì cả Foo và Bar đều implement interface ObjSay do đó phải dùng @Qualifier để chọn
  private ObjSay objSay;

  @Autowired
  private Stone stone;

  @GetMapping("/foo")
  public @ResponseBody String showFoo() {
    return foo.saySomeThing();
  }

  @GetMapping("/say")
  public @ResponseBody String showObjSay() {
    return objSay.saySomeThing();
  }

  @GetMapping("/stone")
  public @ResponseBody String throwStone() {
    return stone.throwSomeThing();
  }

}
