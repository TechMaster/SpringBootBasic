package vn.techmaster.demojpa.component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import org.springframework.stereotype.Component;

@Component
public class LoggedUser {
  private SecureRandom rand;

  public LoggedUser() {
    try {
      rand = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public String get() {    
    String[] users = {"admin", "tester", "dev", "devops", "John"};    
    return users[rand.nextInt(users.length)];
  }
}
