package vn.techmaster.authen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {
  // Tham khảo bài này https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
