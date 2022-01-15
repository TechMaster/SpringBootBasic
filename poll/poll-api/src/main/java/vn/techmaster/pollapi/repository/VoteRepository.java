package vn.techmaster.pollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.techmaster.pollapi.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT COUNT(v.id) from Vote v where v.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);
}
