package com.voting.app.Controller;

import com.voting.app.Entities.Vote;
import com.voting.app.Services.VoteService;
import com.voting.app.payload.ApiResponseDto;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponseDto<Vote>> createVote(@RequestBody @Valid Vote vote) {

        Vote savedVote = voteService.doVoting(vote);
        ApiResponseDto<Vote> response = new ApiResponseDto<>(HttpStatus.CREATED.value(), "Vote Created", savedVote);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/unvote/{voteId}")
    public ResponseEntity<?> unVote(@PathVariable() Integer voteId) {
        Optional<Vote> deletedVote = voteService.unDoVoting(voteId);
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
