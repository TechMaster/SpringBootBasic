package vn.techmaster.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;
import vn.techmaster.blog.testbase.H2TestBase;

public class PostSearchTest extends H2TestBase {
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private PostRepository postRepo;
  @Autowired
  private EntityManager entityManager;

  
  @Test
  void searchByKeyword() {
    Optional<User> optionalUser = userRepo.findByEmail("bob@gmail.com");
    User user = optionalUser.get();
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    user.addPost(post);
    postRepo.save(post);
    userRepo.flush();
    SearchSession searchSession = Search.session(entityManager);

    SearchResult<Post> result = searchSession.search(Post.class)
        .where(f -> f.match().fields("title", "content").matching("Spring")).fetch(20);

    List<Post> hits = result.hits();
    assertThat(hits.size()).isPositive();
  }
}
