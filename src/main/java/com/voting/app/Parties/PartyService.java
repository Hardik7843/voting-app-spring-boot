package com.voting.app.Parties;

import org.springframework.stereotype.Service;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public Party getPartyDetail(String id) {
        return partyRepository.findParty(id);
    }
}
