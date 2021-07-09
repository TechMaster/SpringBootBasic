package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demojpa.model.blog.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
