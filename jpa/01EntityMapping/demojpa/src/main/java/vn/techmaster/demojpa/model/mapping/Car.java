package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.AccessType;

@Entity
@Access(AccessType.FIELD) 
public class Car {
  //id, model,  maker, year
  @Id private long id;
  private String model;
  private String maker;
  private int year;

  @Override
  public String toString() {
    return "Car [id=" + id + ", maker=" + maker + ", model=" + model + ", year=" + year + "]";
  }
}
