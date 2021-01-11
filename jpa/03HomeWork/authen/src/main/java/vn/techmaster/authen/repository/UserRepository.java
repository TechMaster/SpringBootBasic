package vn.techmaster.authen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.authen.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
