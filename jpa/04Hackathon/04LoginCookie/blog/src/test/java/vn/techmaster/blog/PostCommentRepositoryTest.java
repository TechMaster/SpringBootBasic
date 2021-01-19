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
    User alice = userRepo.findByEmail("alice@gmail.com").get();
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
    User alice = userRepo.findByEmail("alice@gmail.com").get();
    userRepo.save(bob);
    userRepo.save(alice);

    Post post1 = new Post("Hà nội có tuyết rơi", "Hôm nay trời quá lạnh. Tuyết rời đầy");
    bob.addPost(post1);

    Post post2 = new Post("MU đại thắng Chelsea", "Trận đấu sớm hôm nay MU đã thắng Chelsea 4-1");
    alice.addPost(post2);

    Comment comment1 = new Comment("Tôi cực thích lạnh");
    comment1.setCommenter(alice);
    post1.addComment(comment1);

    Comment comment2 = new Comment("Tôi sợ lạnh");
    comment2.setCommenter(bob);
    post1.addComment(comment2);

    Comment comment3 = new Comment("Paul Pogba đã quá hay. Soljkaer vững ghế");
    comment3.setCommenter(bob);
    post2.addComment(comment3);

    

    postRepo.save(post1);
    postRepo.save(post2);

    //commentRepo.save(comment1);
    //commentRepo.save(comment2);
    //commentRepo.save(comment3);

    userRepo.flush();
    postRepo.flush();
    //commentRepo.flush();

    assertThat(comment1.getCommenter()).isEqualTo(alice);
    List<Post> bobPosts = bob.getPosts();
    assertThat(bobPosts.get(0).getComments().get(0)).isEqualTo(comment1);
    assertThat(bobPosts.get(0).getComments().get(1)).isEqualTo(comment2);     
    assertThat(bob.getComments().size()).isEqualTo(2);
    assertThat(bobPosts.get(0).getComments().get(0).getCommenter()).isEqualTo(alice);
  }
}
