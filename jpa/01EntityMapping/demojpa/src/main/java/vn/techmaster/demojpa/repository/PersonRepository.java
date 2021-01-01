package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demojpa.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
}
