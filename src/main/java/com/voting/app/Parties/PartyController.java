package com.voting.app.Parties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/about/{id}")
    public Party getPartyDetail(@PathVariable String id) {
        return partyService.getPartyDetail(id);
    }

    // @PostMapping("/register")
    // public String postMethodName(@RequestBody Party party) {
    // //TODO: process POST request

    // return entity;
    // }

}
