package vn.techmaster.demojpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.blog.Post;
import vn.techmaster.demojpa.model.blog.Comment;

@DataJpaTest
public class PostCommentTest {
  @Autowired
  private TestEntityManager tem;
  private Post post1;
  private Comment comment1, comment2;

  @BeforeEach
  public void initializeData() {
    post1 = new Post("Ronaldo đóng phím 45+");
    comment1 = new Comment("Cậu ý quá tài năng");
    comment2 = new Comment("Cậu ý nên học lập trình sẽ tốt hơn");
    post1.addComment(comment1);
    post1.addComment(comment2);
    tem.persist(post1); //Tại sao không cần persist comment1 và comment2?
    tem.flush();
  }

  @AfterEach
  public void afterEachTest() {
    // Sau mỗi bài test thì xoá dữ liệu đi cho sạch, để bài test tiếp theo chạy
    tem.clear();
  }

  @Test
  public void insertPostAndSeveralComments() {
    System.out.println(comment1.getId());

    assertThat(post1.getComments()).hasSize(2);
    assertThat(comment2.getPost().getId()).isEqualTo(post1.getId());
  }

  /*@Test
  public void testCascadePersist() {
    System.out.println(comment1.getId());

    assertThat(post1.getComments()).hasSize(2);
    assertThat(comment2.getPost().getId()).isEqualTo(post1.getId());
  }*/



  @DisplayName("Remove post, cascade remove all comments")
  @Test
  public void removeComments() {
    long comment1Id = comment1.getId();
    long comment2Id = comment2.getId();
    tem.remove(post1);
    tem.flush();
    Comment findComment1 = tem.find(Comment.class, comment1Id);
    Comment findComment2 = tem.find(Comment.class, comment2Id);
    assertThat(findComment1).isNull();
    assertThat(findComment2).isNull();
  }

  /*
  Hãy thử đặt orphanRemoval = false rồi chạy lại
  */
  @DisplayName("Orphan comments should be removed")
  @Test
  public void removeOrphanComment() {
    long comment1Id = comment1.getId();
    long comment2Id = comment2.getId();
    post1.removeComment(comment1);
    post1.removeComment(comment2);
    tem.flush();

    Comment findComment1 = tem.find(Comment.class, comment1Id);
    Comment findComment2 = tem.find(Comment.class, comment2Id);

    assertThat(findComment1).isNull();
    assertThat(findComment2).isNull();
  }

  @Test
  @Sql({"/post.sql"})
  public void LazyOrEager(){
    Comment findComment1 = tem.find(Comment.class, 1L);
    System.out.println("-----");
    Post post = findComment1.getPost();
    System.out.println(post.getTitle());
    assertThat(post.getTitle()).isNotNull();
  }




}
