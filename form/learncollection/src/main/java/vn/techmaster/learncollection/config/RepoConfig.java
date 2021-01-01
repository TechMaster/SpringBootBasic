package vn.techmaster.learncollection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.learncollection.repository.PersonRepositoryCSV;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@Configuration
public class RepoConfig {
  @Value("${csvFile}")
  private String csvFile;

  /*@Bean  
  PersonRepositoryInterface personRepository(){
    return new PersonRepositoryCSV(csvFile);
  }*/
}