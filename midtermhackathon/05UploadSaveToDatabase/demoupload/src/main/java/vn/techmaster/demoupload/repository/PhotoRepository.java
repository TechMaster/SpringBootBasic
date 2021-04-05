package vn.techmaster.demoupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demoupload.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
  
}
