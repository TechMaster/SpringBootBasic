package vn.techmaster.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.UserRepository;

@DataJpaTest
public class PostRepositoryTest {
  @Autowired
  private UserRepository userRepo;
  
  @Test
  void addNewPost() {
    Optional<User> user = userRepo.findByEmail("bob@gmail.com");
    assertThat(user).isPresent();
    
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    user.get().addPost(post);
    userRepo.flush();
    assertThat(post.getAuthor()).isEqualTo(user.get());
    
  }
}
