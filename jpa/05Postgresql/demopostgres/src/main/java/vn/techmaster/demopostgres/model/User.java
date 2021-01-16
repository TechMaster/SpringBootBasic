package vn.techmaster.demopostgres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "date")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id private long id;

  @Column(nullable = false, length = 64)
  private String fullname;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;
}