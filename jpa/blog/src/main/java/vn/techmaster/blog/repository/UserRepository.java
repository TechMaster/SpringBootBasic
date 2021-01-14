package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.blog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
