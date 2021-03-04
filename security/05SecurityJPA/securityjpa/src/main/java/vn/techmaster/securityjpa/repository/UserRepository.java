package vn.techmaster.securityjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.techmaster.securityjpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  /*@Query("SELECT u FROM user u WHERE u.username = :username")
  public User getUserByUsername(@Param("username") String username);*/
  
  public Optional<User> findByUsername(String username);
}