package com.voting.app.Services;

import com.voting.app.Entities.Party;
import com.voting.app.Entities.Vote;
import com.voting.app.Exceptions.ResourceConflict;
import com.voting.app.Exceptions.ResourceNotFound;
import com.voting.app.Repositories.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final PartyService partyService;


    public VoteService(VoteRepository voteRepository, PartyService partyService) {
        this.voteRepository = voteRepository;
        this.partyService = partyService;
    }

    public Optional<Vote> DoVoting(Vote vote) {
        Party party = partyService.getPartyDetail(vote.getPartyId());

        if (party == null) {
            throw new ResourceNotFound("Party does not exist");
        }

        Optional<Vote> newVote = voteRepository.findVoteByUserIdAndPartyId(vote.getUserId(), vote.getPartyId());
        if (newVote.isPresent()) {
            throw new ResourceConflict("Vote Already Exists");
        }
        return Optional.of(voteRepository.save(vote));

    }

    public Optional<Vote> UnDoVoting(Integer voteId) {

        Optional<Vote> deletedVote = voteRepository.findById(voteId);
        deletedVote.ifPresent(voteRepository::delete);
        return deletedVote;
    }

    public Optional<Vote> getVotingDetail(Integer voteId) {
        Optional<Vote> vote = voteRepository.findById(voteId);
        return vote;
    }


}
