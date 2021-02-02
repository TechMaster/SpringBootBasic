package vn.techmaster.bugreport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "comment")
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 3000, nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Bug bug; //Mỗi comment phải gắn vào một bug

  @ManyToOne(fetch = FetchType.LAZY)
  private User user; //Mỗi comment phải do một commenter viết

  public void setUser(User user) {
    user.getComments().add(this);
    this.user = user;
  }
}
