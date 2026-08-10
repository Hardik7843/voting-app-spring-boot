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
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVote);
    }

    @PutMapping("/unvote/{voteId}")
    public ResponseEntity<?> unVote(@PathVariable() Integer voteId) {
        Optional<Vote> deletedVote = voteService.UnDoVoting(voteId);
        if (!deletedVote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vote Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deletedVote);
    }


}
