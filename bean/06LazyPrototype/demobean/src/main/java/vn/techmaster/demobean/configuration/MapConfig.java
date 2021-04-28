package vn.techmaster.demobean.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;

import vn.techmaster.demobean.bean.StreetMap;

@Configuration
public class MapConfig {
  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //Đừng khởi tạo đối tượng theo kiểu Singleton truyền thống nữa!
  //Hãy thử bỏ @Scope("prototype") rồi refresh trình duyệt ở địa chỉ http://localhost:8080/route vài lần
  public StreetMap streetmap() {    
    return new StreetMap();
  }

/*
  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, 
  proxyMode = ScopedProxyMode.TARGET_CLASS) // Khi không muốn sử dụng @Lazy với @Autowired, 
  // mà muốn inject 1 prototype bean vào 1 singleton bean, thì có thể sử dụng proxy mode
  public StreetMap streetmap() {    
    return new StreetMap();
  }
*/
}