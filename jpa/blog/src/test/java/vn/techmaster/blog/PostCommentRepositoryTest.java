package vn.techmaster.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.CommentRepository;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;

@DataJpaTest
public class PostCommentRepositoryTest {
  @Autowired  private UserRepository userRepo;
  @Autowired  private PostRepository postRepo;
  @Autowired  private CommentRepository commentRepo;

  @Test
  void commentAPostSimple(){
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    User alice = userRepo.findByEmail("bob@gmail.com").get();
    userRepo.save(bob);
    userRepo.save(alice);

    Post post = new Post("Hà nội có tuyết rơi", "Hôm nay trời quá lạnh. Tuyết rời đầy");
    bob.addPost(post);


    Comment comment = new Comment("Tôi cực thích lạnh");
    comment.setCommenter(alice);
    post.addComment(comment);

    userRepo.flush();
    assertThat(comment.getCommenter()).isEqualTo(alice);
    List<Post> bobPosts = bob.getPosts();
    assertThat(bobPosts.get(0).getComments().get(0)).isEqualTo(comment);
  }

  @Test
  void commentAPostComplex(){
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    User alice = userRepo.findByEmail("bob@gmail.com").get();
    userRepo.save(bob);
    userRepo.save(alice);

    Post post = new Post("Hà nội có tuyết rơi", "Hôm nay trời quá lạnh. Tuyết rời đầy");
    bob.addPost(post);


    Comment comment1 = new Comment("Tôi cực thích lạnh");
    comment1.setCommenter(alice);
    post.addComment(comment1);

    Comment comment2 = new Comment("Tôi sợ lạnh");
    comment2.setCommenter(bob);
    post.addComment(comment2);

    userRepo.flush();
    assertThat(comment1.getCommenter()).isEqualTo(alice);
    List<Post> bobPosts = bob.getPosts();
    assertThat(bobPosts.get(0).getComments().get(0)).isEqualTo(comment1);
    assertThat(bobPosts.get(0).getComments().get(1)).isEqualTo(comment2);
  }
}
