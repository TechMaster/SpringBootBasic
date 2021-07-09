package vn.techmaster.jpa.demoonetomany;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.jpa.demoonetomany.entity.Comment;
import vn.techmaster.jpa.demoonetomany.entity.Post;


@DataJpaTest
public class PostCommentTest {
  @Autowired
  private EntityManager tem;

  @AfterEach
  public void afterEachTest() {
    // Sau mỗi bài test thì xoá dữ liệu đi cho sạch, để bài test tiếp theo chạy
    tem.clear();
  }

  @Test
  public void insertPostAndSeveralComments() {
    Post post = new Post("1st post");
    post.addComment(new Comment("1st review"));
    post.addComment(new Comment("2nd review"));
    tem.persist(post);
    tem.flush();
  }

  @Test
  public void testLazy() {
    Post post = new Post("1st post");
    post.addComment(new Comment("1st review"));
    post.addComment(new Comment("2nd review"));
    tem.persist(post);
    tem.flush();

    //Post post2 = tem.find(Post.class, 1L);
    //System.out.println("Post is :" + post2);
    //List<Comment> comments = tem.createQuery("select Comment from Comment", Comment.class).getResultList();
    //System.out.println("all comments: " + comments);
    Comment comment = tem.find( Comment.class, 1L );
    System.out.println("Comment is :" + comment);
    //System.out.println("Post is :" + comment.getPost());
  }

}
