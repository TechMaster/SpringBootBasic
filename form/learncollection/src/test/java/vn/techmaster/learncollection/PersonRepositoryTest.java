package vn.techmaster.learncollection;

import java.util.Comparator;
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

	@Test	
	public void getSortedCities(){
		List<String> sortedCities = personRepository.getSortedCities();
		sortedCities.forEach(System.out::println);  //In theo tất các thành phố ra để kiểm tra xem có sắp xếp không
	/*
		Cách này viết dài
		assertThat(sortedCities).contains("Paris", "Dubai");
		assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder());*/

		//Cách này chain các điều kiện test với nhau ngắn gọn và đẹp hơn
		assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder())
		.contains("Berlin", "Budapest", "Buenos Aires", "Copenhagen", "Hanoi", "Jakarta","Mexico City","Zagreb");
	}

	@Test	
	public void getSortedJobs(){
		List<String> sortedJobs = personRepository.getSortedJobs();
		sortedJobs.forEach(System.out::println); 

		assertThat(sortedJobs).isSortedAccordingTo(Comparator.naturalOrder())
		.contains("Pole Dancer", "Bartender", "Developer", "Personal Trainer", "Soldier", "Teacher", "Taxi Driver", "Nurse", "Musician");

	}

	@Test
	public void sortPeopleByFullNameReversed() {
		List<Person> sortedPeople = personRepository.sortPeopleByFullNameReversed();
		sortedPeople.forEach(person -> System.out.println(person.getFullname()));
		assertThat(sortedPeople).isSortedAccordingTo(Comparator.comparing(Person::getFullname).reversed());
	}

}
