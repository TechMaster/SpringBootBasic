package vn.techmaster.learncollection;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	PersonRepositoryInterface personRepository;

	@Test
	public void getAll() {
		List<Person> people = personRepository.getAll();
		assertThat(people).hasSize(20);		
	}

}
