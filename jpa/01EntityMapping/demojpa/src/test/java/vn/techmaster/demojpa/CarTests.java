package vn.techmaster.demojpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import vn.techmaster.demojpa.model.mapping.Car;

@DataJpaTest
@Sql({"/car.sql"})
public class CarTests {
  @Autowired
  private EntityManager tem;  
  /* TestEntityManager chỉ là một subset của EntityManager nó không có chức năng Query
  Thực tế EntityManager có nhiều chức năng Query
  */

  @Test
  @DisplayName("Lấy ra một xe ô tô theo id")
  public void getACar(){
    //(4, 'Crown Victoria', 'Ford', 2004);
    Car car = tem.find(Car.class, 4L);
    assertThat(car).isNotNull().extracting("model").isEqualTo("Crown Victoria");
  }

  @Test
  @DisplayName("Lấy danh sách ô tô sắp xếp theo Model và Year")
  public void getSortedCarByModelYear() {
    Query querygetSortedCarByModelYear = tem.createNativeQuery("SELECT * FROM car ORDER BY model, year", Car.class);
    List<Car> sortedCars = querygetSortedCarByModelYear.getResultList();
    sortedCars.forEach(System.out::println);
    assertThat(sortedCars).isSortedAccordingTo(Comparator.comparing(Car::getModel).thenComparingInt(Car::getYear));

  }
}
