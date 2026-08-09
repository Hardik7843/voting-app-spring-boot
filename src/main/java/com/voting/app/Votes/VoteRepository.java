package com.voting.app.Votes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    public Optional<Vote> findVoteByUserIdAndPartyId(String userId, Integer partyId);
}
