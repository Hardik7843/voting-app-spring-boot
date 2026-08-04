package com.voting.app.Votes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteReposity extends JpaRepository<Vote, Integer> {

}
