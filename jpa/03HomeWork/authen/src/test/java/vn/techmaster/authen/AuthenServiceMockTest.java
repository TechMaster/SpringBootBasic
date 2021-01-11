package vn.techmaster.authen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import vn.techmaster.authen.config.AppConfig;
import vn.techmaster.authen.service.AuthenService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AppConfig.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AuthenServiceMockTest {

  @Autowired  private PasswordEncoder encoder;
 
  private AuthenService authenService;
  //https://reflectoring.io/unit-testing-spring-boot/
  @BeforeAll
  private void initService() {
    authenService = new AuthenService(encoder);
  }

  @Test
  public void testHashedPassword2(){
    String hashedPass = authenService.hashPassword("abcde");
    System.out.println(hashedPass);
    assertThat(hashedPass.length()).isGreaterThan(10);
  }
  
}
