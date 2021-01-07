package vn.techmaster.worldpopulation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.worldpopulation.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
  
}