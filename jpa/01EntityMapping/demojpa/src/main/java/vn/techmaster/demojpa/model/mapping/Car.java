package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.AccessType;

@Entity(name ="oto")
@Table(name = "car")
//@Access(AccessType.FIELD) //https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/access-type.html
public class Car {
  private long id;
  private String model;
  private String maker;
  private int year;

  @Override
  public String toString() {
    return "Car [id=" + id + ", maker=" + maker + ", model=" + model + ", year=" + year + "]";
  }
  
  @Id
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  @Access(AccessType.PROPERTY)
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
