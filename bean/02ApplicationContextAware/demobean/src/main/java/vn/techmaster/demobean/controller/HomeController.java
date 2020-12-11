package vn.techmaster.demobean.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demobean.component.Zip;

@Controller
public class HomeController {

  private Zip zip1; //Không sử dụng annotaton @Autowired
  
  @Autowired //Tự động load đối tượng kiểu Zip vào ApplicationContext
  private Zip zip2;

  @Autowired //biến zip3 thực ra vẫn giống biến zip2 vì nó đều được là 1 đối tượng duy nhất trong ApplicationContext
  private Zip zip3;

  @Autowired
  ApplicationContext appContext;

  @ResponseBody
  @GetMapping(value="/appcontext", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    StringBuilder sb = new StringBuilder();
    sb.append("Display Name = " + appContext.getDisplayName() + "<br><br> ");
    
    String[] allBeanNames = appContext.getBeanDefinitionNames(); //Lấy tất cả tên bean trong AppContext
    
    Arrays.sort(allBeanNames); //Sắp xếp theo thứ tự A-Z
    
    for(String beanName : allBeanNames) {
      sb.append(beanName + "<br>"); //Thêm vào StringBuilder
    }
    return sb.toString();
  }

  //Hãy quan sát dữ liệu trả về liên tục thay đổi sau mỗi lần refresh
  @ResponseBody
  @GetMapping(value="/zip", produces=MediaType.TEXT_HTML_VALUE)
  public String getZip() {
    zip1 = new Zip();
    StringBuilder sb = new StringBuilder("zip1 liên tục đổi " + zip1.toString() + "<br>");
    sb.append("zip2   không đổi " + zip2.toString() + "<br>");
    sb.append("zip3   không đổi " + zip3.toString());
    return sb.toString();
  }
  
}
