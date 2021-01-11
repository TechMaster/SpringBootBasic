package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.mapping.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{
  
}