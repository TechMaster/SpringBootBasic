package vn.techmaster.authen.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
  @Id
  private String id;

  private String fullname;

  @NaturalId
  private String email;

  private String hashedPassword;

  public User(String fullname, String email, String password) {
    this.fullname = fullname;
    this.email = email;
  }


  //------
  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public void addTag(Role role) {
    roles.add(role);
    role.getUsers().add(this);
  }

  public void removeRole(Role role) {
    roles.remove(role);
    role.getUsers().remove(this);
  }

  // -------
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
  @JoinColumn(name = "user_id")
  private List<Event> events = new ArrayList<>();

  public void addEvent(Event event) {
    events.add(event);
    event.setUser(this);
  }

 

}
