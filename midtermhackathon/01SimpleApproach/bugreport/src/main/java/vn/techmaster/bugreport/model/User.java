package vn.techmaster.bugreport.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;

  @Column(nullable = false, length = 64)
  private String fullname;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated (EnumType.ORDINAL)
  private Role role;

  //Một User tạo ra nhiều Bug
  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "user_id")
  private List<Bug> bugs = new ArrayList<>();
  public void addBug(Bug bug) {
    bugs.add(bug);
    bug.setUser(this);
  }

  public void removeBug(Bug bug) {
    bugs.remove(bug);
    bug.setUser(null);
  }

  //Một User viết nhiều Comment
  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "user_id")
  private List<Comment> comments = new ArrayList<>();
  //Tại sao không cần hàm addComment ở đây? Bởi vì chúng ta addComment cho từng Bug

  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setUser(null);
  }
}
