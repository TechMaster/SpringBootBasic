package vn.techmaster.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.techmaster.blog.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  
}
