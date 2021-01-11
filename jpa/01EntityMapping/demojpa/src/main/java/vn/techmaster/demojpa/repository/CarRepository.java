package vn.techmaster.demojpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.mapping.Car;
import vn.techmaster.demojpa.model.mapping.MakerCount;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
  List<Car> findByModel(String model);
  Optional<Car> findById(Long id);
  List<Car> findByOrderByYearAsc();
  Optional<Car> findByModelAndYear(String model, int year);

  @Query("SELECT o FROM oto AS o WHERE o.year=2009")
  List<Car> listCarIn2009();

  @Query("SELECT o FROM oto AS o WHERE o.year=:year")
  List<Car> listCarInYear(@Param("year") int year);

  /*@Query("SELECT new vn.techmaster.demojpa.model.mapping.MakerCount(c.maker, COUNT(c.maker)) " + 
  "FROM oto AS c GROUP BY c.maker")
  List<MakerCount> countByMaker();*/
}

