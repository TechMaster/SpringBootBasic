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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bug")
@Table(name = "bug")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bug {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 300, nullable = true)
  private String title;

  @Column(length = 3000, nullable = false)
  private String content;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  public static final int PHOTO_LIMIT = 3;
  //------ Một bug có thể gắn nhiều photo. Nếu muốn giới hạn số photo hãy bổ xung điều kiện trong hàm addPhoto
  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @JoinColumn(name = "bug_id")
  private List<Photo> photos = new ArrayList<>();
  public void addPhoto(Photo photo ) throws BugException{
    if (photos.size() == PHOTO_LIMIT) {
      throw new BugException("Số lượng photo cho mỗi bug không được vượt quá " + PHOTO_LIMIT);
    }
    photos.add(photo);
    photo.setBug(this);
  }

  public void removePhoto(Photo photo) {
    photos.remove(photo);
    photo.setBug(null);
  }
  //--- Nhiều bug gắn vào một user
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;  //Tác giả viết post


  //------Một bug sẽ có nhiều comment trao đổi giữa người tạo bug và các supporter
  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @JoinColumn(name = "bug_id")
  private List<Comment> comments = new ArrayList<>();
  public void addComment(Comment comment) throws BugException{
    if (comment.getUser().getRole() == Role.CUSTOMER 
    &&  comment.getUser().getId() != this.user.getId()) {
      throw new BugException("Người dùng không được comment vào bug không phải do mình tạo ra");
    }

    comments.add(comment);
    comment.setBug(this);
  }

  public void removeComment(Comment comment) {
      comments.remove(comment);
      comment.setBug(null);
  }  
}
