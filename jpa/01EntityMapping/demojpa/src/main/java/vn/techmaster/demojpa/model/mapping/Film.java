package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="movie")
@Data
public class Film {
  @Id private long id;
  private String title;
  private int year;  
}
