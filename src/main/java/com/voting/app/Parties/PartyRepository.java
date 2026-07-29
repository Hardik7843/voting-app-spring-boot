package com.voting.app.Parties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartyRepository extends JpaRepository<Party, Integer> {

    Party findPartyById(Integer id);

    @Query("SELECT p FROM parties p WHERE p.name = :name")
    Party findPartyByName(String name);

    Party findPartyByCode(String code);
}
