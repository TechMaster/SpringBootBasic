package vn.techmaster.demobean.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.FunRepository;

@Configuration
public class FunRepositoryConfig {
  @Value("${engineType}")
  private String engineType;

  @Bean
  public FunRepository funRepo() {
    return new FunRepository(engineType);
  }  
}
