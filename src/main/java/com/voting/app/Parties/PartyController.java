package com.voting.app.Parties;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Party> getPartyDetail(@PathVariable Integer id) {
        Party party = partyService.getPartyDetail(id);

        return ResponseEntity.ok(party);
    }

    @PostMapping("/register")
    public ResponseEntity<Party> registerParty(@RequestBody @Valid Party party) {

        Party createdParty = partyService.createNew(party);
        URI location = URI.create("/party/" + createdParty.getId().toString());

        return ResponseEntity.created(location).body(createdParty);
    }

}
