package vn.techmaster.authen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import vn.techmaster.authen.model.User;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserTest {

  @Autowired
  private TestEntityManager tem;

  @Test
  public void createNewUser(){
    String hashedPassword = "qwew3221321-";
    User user = new User("Trịnh Minh Cường", "cuong@techmaster.vn", hashedPassword);
    System.out.println(user.getId());
    System.out.println(hashedPassword);
    assertThat(user.getId().length()).isGreaterThan(10);
    
  }
  
}
