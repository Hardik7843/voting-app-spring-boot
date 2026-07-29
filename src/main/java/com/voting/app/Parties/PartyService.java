package com.voting.app.Parties;

import org.springframework.stereotype.Service;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public Party getPartyDetail(Integer id) {
        return partyRepository.findPartyById(id);

    }

    public Party createNew(Party party) {
        return partyRepository.save(party);
    }
}
