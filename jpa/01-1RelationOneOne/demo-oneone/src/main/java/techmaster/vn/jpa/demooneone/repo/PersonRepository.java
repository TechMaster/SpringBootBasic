package techmaster.vn.jpa.demooneone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import techmaster.vn.jpa.demooneone.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
