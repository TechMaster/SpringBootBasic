package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.blog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
