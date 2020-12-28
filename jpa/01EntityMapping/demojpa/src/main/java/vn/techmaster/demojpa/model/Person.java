package vn.techmaster.demojpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person {
  @Id  
  Long id;
  @Column(name = "first_name")
  String firstName;
  @Column(name = "last_name")
  String lastName;
  String email;
  String gender;
  @Transient
  String fullName;

  public String getFullName(){
    return firstName + lastName;
  }
}
