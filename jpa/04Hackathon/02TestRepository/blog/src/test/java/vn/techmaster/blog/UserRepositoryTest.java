package vn.techmaster.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blog.repository.UserRepository;


@DataJpaTest
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepo;
  
  
  @Test
  @DisplayName("Tìm user theo email")
  void findByEmail() {
    var user = userRepo.findByEmail("bob@gmail.com");
    assertThat(user).isPresent();
    assertThat(user.get().getEmail()).isEqualTo("bob@gmail.com");
  }

  @Test
  @DisplayName("Tìm user theo id")
  void findById() {
    var user = userRepo.findById(2L);
    assertThat(user).isPresent();
    assertThat(user.get().getEmail()).isEqualTo("alice@gmail.com");
  }
}
