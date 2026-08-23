package com.voting.app.Parties;

import com.voting.app.Exceptions.ResourceConflict;
import com.voting.app.Exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public Party getPartyDetail(Integer id) {
        Party party = partyRepository.findPartyById(id);
        if (party == null) {
            throw new ResourceNotFound("Party not found");
        }
        return party;
    }

    public Party createNew(Party party) {

        Optional<Party> existingParty = partyRepository.findPartyByCode(party.getCode());
        if (existingParty.isPresent()) {
            throw new ResourceConflict("Party already exists");
        }

        return partyRepository.save(party);
    }
}
