package com.voting.app.Votes;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }


    public Optional<Vote> DoVoting(Vote vote) {

        Optional<Vote> newVote = voteRepository.findVoteByUserIdAndPartyId(vote.getUserId(), vote.getPartyId());

        if(newVote.isPresent())
        {
            throw new IllegalStateException("Vote Already Exists");
        }
        return Optional.of(voteRepository.save(vote));
        
    }

    public Optional<Vote> UnDoVoting(Vote vote) {

        Optional<Vote> deletedVote = voteRepository.findById(vote.getId());

        deletedVote.ifPresent(voteRepository ::delete);

        return deletedVote;
    }

}
