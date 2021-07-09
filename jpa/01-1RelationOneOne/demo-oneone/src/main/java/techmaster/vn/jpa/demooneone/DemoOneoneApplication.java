package techmaster.vn.jpa.demooneone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import techmaster.vn.jpa.demooneone.model.Address;
import techmaster.vn.jpa.demooneone.model.Person;
import techmaster.vn.jpa.demooneone.repo.AddressRepository;
import techmaster.vn.jpa.demooneone.repo.PersonRepository;

@SpringBootApplication
public class DemoOneoneApplication implements CommandLineRunner {

	@Autowired
	PersonRepository personRepo;
	@Autowired
	AddressRepository addressRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoOneoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Address address = Address.builder().city("Hanoi").province("Bac Tu Liem").build();
		addressRepo.save(address);

		Person person = Person.builder().name("Thinh").address(address).build();
		personRepo.save(person);

	}

}
