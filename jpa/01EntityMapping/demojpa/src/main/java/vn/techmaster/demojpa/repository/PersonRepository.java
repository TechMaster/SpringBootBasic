package vn.techmaster.demojpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.mapping.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, 
PersonRepositoryCustom {

  @Query("SELECT new vn.techmaster.demojpa.repository.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> countByJob();

  @Query("SELECT new vn.techmaster.demojpa.repository.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> findTopJobs(Pageable pageable);

  @Query("SELECT new vn.techmaster.demojpa.repository.CityJobCount(p.city, p.job, COUNT(*)) " +
  "FROM person AS p GROUP BY p.city, p.job")
  List<CityJobCount> countJobsInCity();

  @Query("SELECT new vn.techmaster.demojpa.repository.JobSalary(p.job, AVG(p.salary)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobSalary> jobAverageSalary();

  @Query("SELECT new vn.techmaster.demojpa.repository.CityAvgSalary(city, AVG(salary)) " +
  "FROM person GROUP BY city ORDER BY 2 DESC")
  List<CityAvgSalary> cityAverageSalary(Pageable pageable);

  @Query("SELECT new vn.techmaster.demojpa.repository.CityJobCount(city, job, COUNT(job)) " +
  "FROM person GROUP BY city, job HAVING job = ?1 ORDER BY 3 DESC")
  List<CityJobCount> findCitiesHaveMostSpecificJob(String job, Pageable pageable);
}
