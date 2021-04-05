package vn.techmaster.bookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.bookstore.model.Contact;
public interface ContactRepository extends JpaRepository<Contact, Long>{
  
}
