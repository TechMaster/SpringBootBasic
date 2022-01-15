package vn.techmaster.pollapi.service;

import org.springframework.beans.factory.annotation.Autowired;

import vn.techmaster.pollapi.repository.PollRepository;
import vn.techmaster.pollapi.repository.VoteRepository;

public class PollService {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;

    public long countPollByCreatedUserId(Long userId) {
        return pollRepository.countByCreatedBy(userId);
    }

    public long countVoteByUserId(Long userId) {
        return voteRepository.countByUserId(userId);
    }
}
