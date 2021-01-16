package vn.techmaster.demojpa.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


import vn.techmaster.demojpa.model.mapping.Person;

public class PersonRepositoryImpl implements PersonRepositoryCustom {
  @Autowired
  @Lazy
  PersonRepository personRepository;

  @PersistenceContext
  private EntityManager em; // Access EntityManager from CRUDRepository
                            // https://dzone.com/articles/accessing-the-entitymanager-from-spring-data-jpa

  @Override
  public Map<String, List<Person>> groupPeopleByCity() {
    return personRepository.findAll().stream().collect(Collectors.groupingBy(Person::getCity));
  }

  class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      return a.getCity().compareTo(b.getCity());
    }
  }

  @Override
  public TreeMap<String, List<Person>> groupPeopleByOrderCity() {
    // https://stackoverflow.com/questions/35116264/java-8-stream-api-tomap-converting-to-treemap
    return personRepository.findAll().stream()
        .collect(Collectors.groupingBy(Person::getCity, TreeMap::new, Collectors.toList()));
  }

  @Override
  public Map<String, CityJobCount> topJobInCity() {
    List<CityJobCount> cityJobCount = personRepository.countJobsInCity();
    // https://stackoverflow.com/questions/51377851/java-streams-how-to-group-by-value-and-find-min-and-max-value-of-each-group

    return cityJobCount.stream().collect(Collectors.toMap(CityJobCount::getCity, Function.identity(),
        BinaryOperator.maxBy(Comparator.comparing(CityJobCount::getCount))));
    /*
     * cách này chỉ lấy được duy nhất một nghề nhiều nhất trong 1 thành phố, mà
     * không lấy được tất cả các nghề nhiều nhất Baghdad Carpenter : 3
     */
  }

  @Override
  public TreeMap<String, List<JobCount>> countAllTopJobsInCity() {
    String query = "SELECT new vn.techmaster.demojpa.repository.CityJobCount(p.city, p.job, COUNT(*) as jobcount) "
        + "FROM person AS p GROUP BY p.city, p.job ORDER BY p.city ASC, jobcount DESC";

    TypedQuery<CityJobCount> typedQuery = em.createQuery(query, CityJobCount.class);

    List<CityJobCount> cityJobCountSorted = typedQuery.getResultList();
    String city = "";
    long maxcount = 0;
    List<JobCount> jobList = new ArrayList<>();
    TreeMap<String, List<JobCount>> result = new TreeMap<>(); // TreeMap có tác dụng sắp xếp key khi thêm phần tử mới

    for (var cityJobCount : cityJobCountSorted) {
      if (!city.equals(cityJobCount.getCity())) {
        city = cityJobCount.getCity();
        jobList = new ArrayList<>();
        maxcount = cityJobCount.getCount();
        jobList.add(new JobCount(cityJobCount.getJob(), maxcount));
      } else { // city đang lặp lại
        if (maxcount == cityJobCount.getCount()) {
          jobList.add(new JobCount(cityJobCount.getJob(), maxcount));
        } else {
          result.put(city, jobList);
        }
      }
    }

    return result;
  }

  @Override
  public Map<String, Double> averageJobAge() {
    return personRepository.findAll().stream()
        .collect(Collectors.groupingBy(Person::getJob, Collectors.averagingInt(Person::getAge)));
    // đọc thêm https://www.baeldung.com/java-groupingby-collector

    // Cải tiến bài này để sắp xếp theo Job ascending hoặc age giảm dần
  }

  @Override
  public LinkedHashMap<String, Double> topAverageJobAge(int top) {

    Map<String, Double> averageJobAge = personRepository.findAll().stream()
    .collect(Collectors.groupingBy(Person::getJob, Collectors.averagingInt(Person::getAge)));

    LinkedHashMap<String, Double> sortedJobAgeDESC = new LinkedHashMap<>();

    averageJobAge.entrySet()
    .stream()
    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    .limit(top) //https://www.baeldung.com/java-stream-skip-vs-limit
    .forEachOrdered(x -> sortedJobAgeDESC.put(x.getKey(), x.getValue()));
    //https://howtodoinjava.com/java/sort/java-sort-map-by-values/

    
    return sortedJobAgeDESC;
  }

  
}

