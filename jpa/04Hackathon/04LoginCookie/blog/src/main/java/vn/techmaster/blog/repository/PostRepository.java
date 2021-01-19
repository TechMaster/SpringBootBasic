package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.blog.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  
}
