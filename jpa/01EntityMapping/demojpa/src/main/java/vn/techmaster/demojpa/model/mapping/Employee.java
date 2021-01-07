package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "employee")
public class Employee {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Transient private String fullname;
  private String firstName;
  private String lastName;

  public Long getId() {
    return id;
  }

  /*
  public void setId(Long id) {
    this.id = id;
  } */

  public String getFullname() {
    return firstName + " " + lastName;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Employee(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
