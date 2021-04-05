package vn.techmaster.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.techmaster.blog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(String email);

  @Query("SELECT DISTINCT u FROM user u LEFT JOIN FETCH u.posts")
  public List<User> findAllUserWithPosts();
}