package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import vn.techmaster.demojpa.model.mapping.Car;

@DataJpaTest
public class CarTests {
  @Autowired
  private TestEntityManager tem;

  @Test
  public void getFirstCar(){
    //(4, 'Crown Victoria', 'Ford', 2004);
    Car car = tem.find(Car.class, 4L);
    System.out.println(car);
    assertThat(car).isNotNull();
  }
}
