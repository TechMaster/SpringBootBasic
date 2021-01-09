package vn.techmaster.demojpa.model.blog;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  public Comment(String title) {
    this.title = title;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  private Post post;

  @PreRemove
  public void beforeRemove() {
    System.out.println("beforeRemove");
  }  
}