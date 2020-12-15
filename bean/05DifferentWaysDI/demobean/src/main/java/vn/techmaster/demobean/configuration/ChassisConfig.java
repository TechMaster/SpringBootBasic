package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.Chassis;
import vn.techmaster.demobean.bean.Door;

@Configuration
public class ChassisConfig {
  @Bean
  public Door door() {    
    return new Door();
  }
  @Bean
  public Chassis chassis() {
    Chassis chassis = new Chassis();
    chassis.setDoor(door()); //Setter based Dependency Injection
    return chassis;
  }
}
