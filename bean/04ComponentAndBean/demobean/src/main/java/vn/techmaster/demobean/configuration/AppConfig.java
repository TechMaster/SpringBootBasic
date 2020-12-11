package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.Engine;

import org.springframework.context.annotation.Bean;


@Configuration
public class AppConfig {
  @Bean
  public Engine myEngine() {
    return new Engine("Ford Engine");
  }
}
