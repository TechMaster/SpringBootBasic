package vn.techmaster.demoupload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity(name = "photo")
@Table(name = "photo")
@Data
public class Photo {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 50, unique = true, nullable = false)
  private String fileName;

  @ManyToOne(fetch = FetchType.LAZY)
  private Bug bug; //Mỗi comment phải gắn vào một bug

  public Photo(String fileName) {
    this.fileName = fileName;
  }
}
