package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vn.techmaster.demojpa.repository.CityAvgSalary;
import vn.techmaster.demojpa.repository.CityJobCount;
import vn.techmaster.demojpa.repository.JobCount;
import vn.techmaster.demojpa.repository.JobSalary;
import vn.techmaster.demojpa.model.mapping.Person;
import vn.techmaster.demojpa.repository.PersonRepository;


@DataJpaTest
@Sql({"/person.sql"})
class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepo;
	
	
	@Test
	void checkAge() {
		Person person = personRepo.getOne(1L);
		System.out.println(person.getAge());
		assertThat(person.getAge() > 0).isTrue();
	}

	@Test
	void groupPeopleByCity() {
		Map<String, List<Person>> result = personRepo.groupPeopleByCity();
		result.keySet().forEach(System.out::println);
		assertThat(result).containsKeys("London", "Wellington", "Buenos Aires", "Stockholm");
		
	}

	@Test
	void groupPeopleByOrderCity() {
		Map<String, List<Person>> result = personRepo.groupPeopleByOrderCity();
		result.keySet().forEach(System.out::println);
		//result.get("Hanoi").forEach(System.out::println);
		var keys = List.copyOf(result.keySet());
		assertThat(keys).isSorted();		
	}

	@Test
	void countByJob(){
		List<JobCount> result = personRepo.countByJob();
		result.forEach(System.out::println);
		assertThat(result).isSortedAccordingTo(Comparator.comparing(JobCount::getCount).reversed());
	}

	@Test
	void findTopJobs(){
		int top = 5;
		List<JobCount> top5Jobs = personRepo.findTopJobs(PageRequest.of(0, top));
		top5Jobs.forEach(System.out::println);
		assertThat(top5Jobs).hasSize(top).
		isSortedAccordingTo(Comparator.comparing(JobCount::getCount).reversed());
	}

	@Test
	void countJobsInCity() {
		List<CityJobCount> result = personRepo.countJobsInCity();
		assertThat(result.size()).isPositive();
	}

	@Test
	void topJobInCity() {
		Map<String, CityJobCount> result = personRepo.topJobInCity();
		result.forEach((city,cityJobCount) -> System.out.println(city + " " + cityJobCount.getJob() + " : " + cityJobCount.getCount()));
		assertThat(result.values().size()).isPositive();
	}

	@Test
	void topAverageJobAge() {
		LinkedHashMap<String, Double> result = personRepo.topAverageJobAge(10);
		assertThat(result.values()).hasSize(10);
	}



	@Test
	void countAllTopJobsInCity() {
		TreeMap<String, List<JobCount>> result = personRepo.countAllTopJobsInCity();
		
		for (String city:result.navigableKeySet()){
			System.out.println(city);
			for (JobCount jobCount:result.get(city)) {
				System.out.println("  " + jobCount.toString());
			}
		}
		assertThat(result.values().size()).isPositive();		
	}

	@Test
	void jobAverageSalary() {
		List<JobSalary> result =  personRepo.jobAverageSalary();
		assertThat(result).hasSizeGreaterThan(0).isSortedAccordingTo(Comparator.comparing(JobSalary::getAveragesalary).reversed());
	}

	@Test
	void cityAverageSalary() {
		int top = 5;
		List<CityAvgSalary> result = personRepo.cityAverageSalary(PageRequest.of(0, top));
		assertThat(result).hasSize(top).isSortedAccordingTo(Comparator.comparing(CityAvgSalary::getAveragesalary).reversed());
	}

	@Test
	void averageJobAge(){
		Map<String, Double> result = personRepo.averageJobAge();
		assertThat(result).isNotEmpty();
		assertThat(result.values()).allSatisfy(a -> {
			assertThat(a > 0 && a < 100).isTrue();
		});
	}

	@Test
	void findCitiesHaveMostSpecificJob() {
		int top = 5;
		String job = "Accountant";
		List<CityJobCount> result = personRepo.findCitiesHaveMostSpecificJob(job, PageRequest.of(0, top));
		assertThat(result).hasSize(top)
		.isSortedAccordingTo(Comparator.comparing(CityJobCount::getCount).reversed())
		.extracting("job")
		.containsOnly(job);
	}
	

}
