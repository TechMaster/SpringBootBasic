package vn.techmaster.worldpopulation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.worldpopulation.model.Country;
import vn.techmaster.worldpopulation.repository.CountryRepository;

@Service
public class CountryService implements ICountryService {

  @Autowired
  private CountryRepository repository;

  @Override
  public List<Country> findAll() {

    return (List<Country>) repository.findAll();
  }

  @Override
  public Optional<Country> getByID(Long id) {
    return repository.findById(id);
  }
}