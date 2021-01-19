package vn.techmaster.testcontainer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.techmaster.testcontainer.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query(value = "SELECT * FROM post LIMIT 2", nativeQuery = true)
  List<Post> getTop2Posts();

  @Query(value = "SELECT p FROM POST AS p")
  List<Post> getTopPosts(Pageable pageable);
}
