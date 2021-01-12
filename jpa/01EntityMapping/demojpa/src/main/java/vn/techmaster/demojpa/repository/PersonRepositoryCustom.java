package vn.techmaster.demojpa.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vn.techmaster.demojpa.model.mapping.Person;

public interface PersonRepositoryCustom {
  Map<String, List<Person>> groupPeopleByCity();
  TreeMap<String, List<Person>> groupPeopleByOrderCity();
}
