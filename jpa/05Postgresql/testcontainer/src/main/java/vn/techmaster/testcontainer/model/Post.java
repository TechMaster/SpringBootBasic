package vn.techmaster.testcontainer.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="POST")
@Table(name="post")
@Data
@NoArgsConstructor
public class Post {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 
  private String title;
  public Post(String title) {
    this.title = title;
  }
}