package vn.techmaster.worldpopulation.service;
import java.util.List;
import java.util.Optional;

import vn.techmaster.worldpopulation.model.Country;

public interface ICountryService{
    List<Country> findAll();
    public Optional<Country> getByID(Long id);
}