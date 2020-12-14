package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.Chasis;
import vn.techmaster.demobean.bean.Door;

@Configuration
public class ChasisConfig {
  @Bean
  public Door door() {    
    return new Door();
  }
  @Bean
  public Chasis chasis() {
    Chasis chasis = new Chasis();
    chasis.setDoor(door()); //Setter based Dependency Injection
    return chasis;
  }
}
