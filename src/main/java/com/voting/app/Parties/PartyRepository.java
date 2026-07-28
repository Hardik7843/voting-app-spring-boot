package com.voting.app.Parties;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, String> {

    Party findParty(String id);

    Party findPartyByName(String name);

    Party findPartyByCode(String code);
}
