package vn.techmaster.pollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.pollapi.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}
