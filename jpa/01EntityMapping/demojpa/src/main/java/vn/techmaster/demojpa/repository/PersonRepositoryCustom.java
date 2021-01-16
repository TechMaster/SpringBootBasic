package vn.techmaster.demojpa.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import vn.techmaster.demojpa.model.mapping.Person;

public interface PersonRepositoryCustom {
  Map<String, List<Person>> groupPeopleByCity();
  TreeMap<String, List<Person>> groupPeopleByOrderCity();

  Map<String, CityJobCount> topJobInCity();

  TreeMap<String, List<JobCount>> countAllTopJobsInCity();

  Map<String, Double> averageJobAge();

  LinkedHashMap<String, Double> topAverageJobAge(int top);
}
