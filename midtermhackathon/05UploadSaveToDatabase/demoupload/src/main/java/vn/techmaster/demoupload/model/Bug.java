package vn.techmaster.demoupload.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.demoupload.exception.BugException;

@Entity(name = "bug")
@Table(name = "bug")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bug {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 300, nullable = true)
  private String title;

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
}
