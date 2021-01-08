package vn.techmaster.authen.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name="event")
@Data
public class Event {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  
}
