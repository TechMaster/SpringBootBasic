package vn.techmaster.testcontainer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.testcontainer.model.Post;
import vn.techmaster.testcontainer.repository.PostRepository;

@Sql("post.sql")
public class PostRepoTest extends PostgresqlTestBase{
  @Autowired
  private PostRepository postRepo;
  
  @BeforeAll
  private void prepareData() {
    Post post1 = new Post("MU đấu Liverpool");
    Post post2 = new Post("Hà nội cực lạnh");
    Post post3 = new Post("Thầy Cường thích đạp xe");
    Post post4 = new Post("Cắm trại dưới trời mưa");
    postRepo.save(post1);
    postRepo.save(post2);
    postRepo.save(post3);
    postRepo.save(post4);
  }

  @Test
  void checkIfExternalDataIsLoaded() {
    List<Post> posts = postRepo.findAll();
    assertThat(posts.size()).isGreaterThan(4);
  }

  @Test
  void insertSomePosts() {
    List<Post> posts = postRepo.findAll();
    assertThat(posts.size()).isPositive();
    Optional<Post> post1 = postRepo.findById(1L);
    assertThat(post1).isPresent();
  }

  @Test
  void getTop2Posts() {
    List<Post> posts = postRepo.getTop2Posts();
    assertThat(posts).hasSize(2);
  }

  @Test
  void getTopPosts() {
    List<Post> posts = postRepo.getTopPosts(PageRequest.of(0, 2));
    assertThat(posts).hasSize(2);
  }
}
