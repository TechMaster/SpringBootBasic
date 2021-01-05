package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "animal")
@Data
public class Animal {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
}
