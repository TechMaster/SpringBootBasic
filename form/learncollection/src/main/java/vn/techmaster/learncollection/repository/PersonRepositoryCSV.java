package vn.techmaster.learncollection.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.learncollection.model.Person;

@Repository
public class PersonRepositoryCSV implements PersonRepositoryInterface {
  private ArrayList<Person> people;

  @Autowired
  public PersonRepositoryCSV(@Value("${csvFile}") String csvFile) {
    people = new ArrayList<>();
    loadData(csvFile);
  }

  private void loadData(String csvFile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + csvFile);
      CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
      Reader reader = new FileReader(file);
      MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
        Person person = mi.next();
        people.add(person);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public List<Person> getAll() {
    return people;
  }

  @Override
  public List<Person> sortPeopleByFullNameReversed() {
    return people.stream().sorted(Comparator.comparing(Person::getFullname).reversed()).collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedCities() {
    /*
     * return people.stream(). sorted(Comparator.comparing(Person::getCity)).
     * map(Person::getCity).collect(Collectors.toList());
     */
    return people.stream().map(Person::getCity).sorted().collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedJobs() {
    return people.stream().map(Person::getJob).sorted().collect(Collectors.toList());
  }

  @Override
  public HashMap<String, Integer> findTop5Citis() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, Integer> findTop5Jobs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, String> findTopJobInCity() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, Integer> groupJobByCount() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, List<Person>> groupPeopleByCity() {
    // TODO Auto-generated method stub
    return null;
  }

 

  @Override
  public HashMap<String, Float> averageCityAge() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, Float> averageJobAge() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, Float> averageJobSalary() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> find5CitiesHaveMostSpecificJob(String job) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, Float> top5HighestSalaryCities() {
    // TODO Auto-generated method stub
    return null;
  }

 
  
}
