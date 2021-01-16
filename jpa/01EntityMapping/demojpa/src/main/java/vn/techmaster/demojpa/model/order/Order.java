package vn.techmaster.demojpa.model.order;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "order")
@Data
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private Address from = new Address();

  @Embedded
  private Address shipTo = new Address();

  private long total;
  private ShippingMethod shippingMethod;

  /*@OneToMany
  @JoinTable(
    
  )*/
}
