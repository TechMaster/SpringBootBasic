package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import vn.techmaster.demojpa.model.mapping.Person;
import vn.techmaster.demojpa.repository.PersonRepository;

@DataJpaTest
class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepo;
	
	@Test
	void checkAge() {
		Person person = personRepo.getOne(1L);
		System.out.println(person.getAge());
		assertThat(person.getAge() > 0).isTrue();
	}

}
