package vn.techmaster.worldpopulation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import vn.techmaster.worldpopulation.service.ICountryService;
import org.springframework.ui.Model;
import java.util.List;
import vn.techmaster.worldpopulation.model.Country;
import java.util.Optional;
@Controller
public class HomeController {
  @Autowired
  private ICountryService countryService;

  @GetMapping("/")
  public String getCountries(Model model) {
    List<Country> countries = countryService.findAll();
    model.addAttribute("countries", countries);
    return "home";
  }

  @GetMapping(value = "/{id}")
  public String getCountryByID(@PathVariable("id") long id, Model model) {
    Optional<Country> country = countryService.getByID(id);
    if (country.isPresent()) {
      model.addAttribute("country", country.get());
    }    
    return "country";
  }
}