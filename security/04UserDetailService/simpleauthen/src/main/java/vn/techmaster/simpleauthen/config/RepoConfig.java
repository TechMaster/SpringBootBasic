package vn.techmaster.simpleauthen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.simpleauthen.repository.BookDao;

@Configuration
public class RepoConfig {

  @Bean
  public BookDao bookDao() {
    return new BookDao("book.csv");
  }
  
}
