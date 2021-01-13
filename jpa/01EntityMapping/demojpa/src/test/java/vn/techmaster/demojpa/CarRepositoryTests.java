package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Car;
import vn.techmaster.demojpa.repository.CarRepository;
import vn.techmaster.demojpa.repository.MakerCount;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Comparator;
import java.util.List;

@DataJpaTest
@Sql({"/car.sql"})
public class CarRepositoryTests {
  @Autowired
  private CarRepository carRepository;;

  @Test
  public void findByIdTest() {
    var car = carRepository.findById(1L);
    assertThat(car.get()).isNotNull();
  }
  
  @Test
  public void findByModelTest() {
    String model = "E-350 Super Duty";
    List<Car> cars = carRepository.findByModel(model);
    assertThat(cars.get(0).getModel()).isEqualTo(model);    
  }

  @Test
  public void findByModelAndYear() {
    var car = carRepository.findByModelAndYear("Boxster", 2008);    
    assertThat(car.get()).extracting("model", "year").containsExactly("Boxster", 2008);
  }

  @Test
  public void findByOrderByYearAscTest() {
    List<Car> cars = carRepository.findByOrderByYearAsc();
    assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getYear));
  }

  @Test
  public void findByOrderByYearDscTest() {
    List<Car> cars = carRepository.findAll(Sort.by("year").descending());
     assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getYear).reversed());
  }

  @Test
  public void findByOrderByModelYearAscTest() {
    List<Car> cars = carRepository.findAll(Sort.by("model", "year"));
    assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getModel).thenComparing(Car::getYear));
  }


  @Test
  public void listCarIn2009Test() {
    List<Car> cars = carRepository.listCarIn2009();
    assertThat(cars).extracting("year").containsOnly(2009);
  }

  @Test
  public void listCarInYearTest() {
    List<Car> cars = carRepository.listCarInYear(2008);
    assertThat(cars).extracting("year").containsOnly(2008);
  }

  @Test
  public void countByMakerTest() {
    List<MakerCount> makerCounts= carRepository.countByMaker();
    makerCounts.forEach(System.out::println);
    assertThat(makerCounts).isSortedAccordingTo(Comparator.comparing(MakerCount::getMaker));
  }

  @Test
  public void top5CarMakerTest() {
    List<MakerCount> top5Makers= carRepository.topCarMaker(PageRequest.of(0, 5));
    top5Makers.forEach(System.out::println);
    assertThat(top5Makers).hasSize(5);
  }

  


}
