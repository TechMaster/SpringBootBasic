package vn.techmaster.demoannotation.model;

import vn.techmaster.demoannotation.annotation.Entity;

@Entity(name="person")
public class Person {
  private String name;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
