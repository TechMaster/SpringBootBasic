package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
		assertThat(result.keySet()).contains("London", "Wellington", "Buenos Aires", "Stockholm");
		
	}

	@Test
	void groupPeopleByOrderCity() {
		Map<String, List<Person>> result = personRepo.groupPeopleByOrderCity();
		result.keySet().forEach(System.out::println);
		var keys = List.copyOf(result.keySet());
		assertThat(keys).isSorted();
		
	}

}
