package com.voting.app.Votes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/election")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/vote")
    public ResponseEntity<?> createVote(Vote vote) {

        Optional<Vote> savedVote = voteService.DoVoting(vote);

        if (savedVote.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVote.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/unvote/{voteId}")
    public ResponseEntity<?> unVote(@PathVariable() Integer voteId) {
        Optional<Vote> deletedVote = voteService.UnDoVoting(voteId);
        if (!deletedVote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vote Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deletedVote);
    }

    @GetMapping("/vote/{id}")
    public ResponseEntity<?> getVote(@PathVariable() Integer id) {
        Optional<Vote> vote = voteService.getVotingDetail(id);
        if (!vote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vote detail not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vote);
    }
}
