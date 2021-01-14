package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
