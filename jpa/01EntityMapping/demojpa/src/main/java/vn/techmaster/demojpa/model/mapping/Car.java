package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.AccessType;

@Entity
@Table(name = "car")
@Access(AccessType.FIELD) //https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/access-type.html
/*
@Id private long id;  --> @Access(AccessType.FIELD)
Cách này có nhiều ưu điểm hơn AccessType.PROPERTY
https://thorben-janssen.com/access-strategies-in-jpa-and-hibernate/

@Id  --> @Access(AccessType.PROPERTY)
public long getId() {
  return id;
}

Có thể trộn lẫn 

*/

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
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
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
