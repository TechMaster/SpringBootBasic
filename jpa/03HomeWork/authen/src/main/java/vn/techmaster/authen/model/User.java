package vn.techmaster.authen.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;
import java.nio.ByteBuffer;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
  @Id
  private String id;

  @Column(nullable = false, length = 64)
  private String fullname;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String hashedPassword;

  public User(String fullname, String email, String hashedPassword) {
    //Generate short id https://gist.github.com/LeeSanghoon/5811136
    this.id = Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(), Character.MAX_RADIX);
    this.fullname = fullname;
    this.email = email;
    this.hashedPassword = hashedPassword;
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
