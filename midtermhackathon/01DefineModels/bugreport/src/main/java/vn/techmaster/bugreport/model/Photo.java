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

@Entity(name = "photo")
@Table(name = "photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 50, unique = true, nullable = false)
  private String filename;

  @ManyToOne(fetch = FetchType.LAZY)
  private Bug bug; //Mỗi comment phải gắn vào một bug
}
