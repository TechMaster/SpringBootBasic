package vn.techmaster.demoupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demoupload.model.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long>{
  
}
