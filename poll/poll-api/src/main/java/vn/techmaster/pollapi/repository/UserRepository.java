package vn.techmaster.pollapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.pollapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUserName(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}
