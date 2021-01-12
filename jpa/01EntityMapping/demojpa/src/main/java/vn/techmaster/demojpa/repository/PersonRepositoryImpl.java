package vn.techmaster.demojpa.repository;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import vn.techmaster.demojpa.model.mapping.Person;

public class PersonRepositoryImpl implements PersonRepositoryCustom {
  @Autowired
  @Lazy
  PersonRepository personRepository;

  @Override
  public Map<String, List<Person>> groupPeopleByCity() {
    return personRepository.findAll().stream().collect(Collectors.groupingBy(Person::getCity));
  }

  class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b)
    {
        return a.getCity().compareTo(b.getCity());
    }
  }

  @Override
  public TreeMap<String, List<Person>> groupPeopleByOrderCity() {
    //https://stackoverflow.com/questions/35116264/java-8-stream-api-tomap-converting-to-treemap
    return personRepository.findAll().stream().collect(Collectors.groupingBy(Person::getCity, TreeMap::new, Collectors.toList()));
  }
}

