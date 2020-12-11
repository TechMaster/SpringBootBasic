package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.Engine;

@Configuration
public class AppConfig {
  @Bean
  public Engine myEngine() {
    return new Engine("Ford Engine");
  }

  @Bean
  public Engine teslaEngine() {
    return new Engine("Electric Tesla Engine");
  }
}