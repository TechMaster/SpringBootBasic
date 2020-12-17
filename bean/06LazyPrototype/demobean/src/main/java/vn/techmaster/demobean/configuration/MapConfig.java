package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import vn.techmaster.demobean.bean.StreetMap;

@Configuration
public class MapConfig {
  @Bean
  @Scope("prototype") //Đừng khởi tạo đối tượng theo kiểu Singleton truyền thống nữa!
  //Hãy thử bỏ @Scope("prototype") rồi refresh trình duyệt ở địa chỉ http://localhost:8080/route vài lần
  public StreetMap streetmap() {    
    return new StreetMap();
  }
}