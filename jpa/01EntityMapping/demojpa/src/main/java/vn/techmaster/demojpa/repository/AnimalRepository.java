package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demojpa.model.mapping.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
  
}