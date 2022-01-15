package vn.techmaster.pollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.pollapi.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
    long countByCreatedBy(Long userId);
}
