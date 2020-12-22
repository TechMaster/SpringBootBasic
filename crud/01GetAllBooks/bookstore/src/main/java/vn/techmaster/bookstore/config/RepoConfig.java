package vn.techmaster.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.bookstore.repository.BookDao;

@Configuration
public class RepoConfig {

  @Bean
  public BookDao bookDao() {
    return new BookDao();
  }  
}
