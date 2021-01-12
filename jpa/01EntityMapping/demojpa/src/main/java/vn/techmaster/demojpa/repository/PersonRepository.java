package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.mapping.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, 
PersonRepositoryCustom {

}
