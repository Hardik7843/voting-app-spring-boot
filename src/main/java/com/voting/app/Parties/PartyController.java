package com.voting.app.Parties;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartyDetail(@PathVariable Integer id) {
        Party party = partyService.getPartyDetail(id);

        return ResponseEntity.ok(party);
    }

    @PostMapping("/register")
    public ResponseEntity<Party> registerParty(@RequestBody Party party) {

        Party createdParty = partyService.createNew(party);
        URI location = URI.create("/party/" + createdParty.getId());

        return ResponseEntity.created(location).body(createdParty);
    }

}
