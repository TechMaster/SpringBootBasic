package vn.techmaster.demobean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demobean.bean.DBRepo;
import vn.techmaster.demobean.bean.Foo;
import vn.techmaster.demobean.bean.Stone;
import vn.techmaster.demobean.component.APC;

@Controller
public class SecondController {

  @Autowired
  private ApplicationContext applicationContext; // Lấy application context

  @Autowired
  @Lazy // Annotation chỉ có tác dụng với Bean @Scope("prototype") nếu
        // @Scope("singleton") thì @Lazy không có tác dụng
  Stone stone;

  @Autowired
  @Qualifier("foo1")
  Foo foo1;

  @Autowired
  @Qualifier("foo2")
  Foo foo2;

  @Autowired
  APC apc;

  @Value("${secret_key}")
  private String seckey;

  @Autowired
  DBRepo dbRepo;
  

  @GetMapping("/stone2")
  public @ResponseBody String throwStone() {
    return stone.throwSomeThing();
  }

  @GetMapping("/foo1")
  public @ResponseBody String breakRock() {
    return foo1.saySomeThing() + " " + foo2.saySomeThing();
  }

  @GetMapping("/apc")
  public @ResponseBody String getAPC() {
    return apc.doSomething();
  }

  @GetMapping("/db")
  public @ResponseBody String db() {
    return dbRepo.connectTo();
  }

  @GetMapping("/secret")
  public @ResponseBody String secret() {
    return seckey;
  }

  /*
  Demo chức năng của @ComponentScan vốn được tích hợp sẵn trong @SpringBootApplication.
  checkBeansPresence sẽ dùng ApplicationContext để kiểm tra xem một bean nào đó được khởi tạo và đăng ký
  trong ApplicationContext hay chưa
  */
  @GetMapping("/scan") // Component Scan quét được cả @Bean lẫn @Component
  public @ResponseBody String componentScan() {
    return checkBeansPresence("foo1", "foo2", "foo", "bar", "stone", "DAO");
  }

  private String checkBeansPresence(String... beans) {
    StringBuilder str = new StringBuilder("ApplicationContext contains following beans:");
    str.append("<br>");
    for (String beanName : beans) {
      if (applicationContext.containsBean(beanName)) {
        str.append(beanName);
        str.append("<br>");
      }
    }
    return str.toString();
  }
}
