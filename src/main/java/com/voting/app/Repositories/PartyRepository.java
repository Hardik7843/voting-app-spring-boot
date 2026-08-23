package com.voting.app.Repositories;

import com.voting.app.Entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Integer> {

    Party findPartyById(Integer id);

    Party findPartyByName(String name);

    Optional<Party> findPartyByCode(String code);
}
