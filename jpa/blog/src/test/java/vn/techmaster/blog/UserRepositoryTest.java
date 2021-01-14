package vn.techmaster.blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.blog.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepo;
  
  @Test
  void findByEmail() {
    var user = userRepo.findByEmail("bob@gmail.com");
    assertThat(user).isPresent();
    assertThat(user.get().getEmail()).isEqualTo("bob@gmail.com");
  }
}
