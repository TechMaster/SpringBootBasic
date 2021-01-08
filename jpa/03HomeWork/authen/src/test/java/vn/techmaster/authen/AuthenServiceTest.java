package vn.techmaster.authen;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.authen.service.AuthenService;
@SpringBootTest
public class AuthenServiceTest {
  @Autowired
  private AuthenService authenService;

  @Test
  public void testHashedPasswordMock() {
    //https://youtu.be/gIb_m06XeQE
    String hashedPass = authenService.hashPassword("abcde");
    System.out.println(hashedPass);
    assertThat(hashedPass.length()).isGreaterThan(10);
  }
  
}
