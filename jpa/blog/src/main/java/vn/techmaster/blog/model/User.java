package vn.techmaster.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String id;

  @Column(nullable = false, length = 64)
  private String fullname;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  //Một User viết nhiều Post
  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "user_id")
  private List<Post> posts = new ArrayList<>();
  public void addPost(Post post) {
    posts.add(post);
    post.setAuthor(this);
  }

  public void removePost(Post post) {
    posts.remove(post);
    post.setAuthor(null);
  }

  //Một User viết nhiều Comment
  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "user_id")
  private List<Comment> comments = new ArrayList<>();
  public void addComment(Comment comment) {
    comments.add(comment);
    comment.setCommenter(this);
  }
  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setCommenter(null);
  }
}
