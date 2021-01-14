package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
}
