package com.voting.app.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    public Vote() {
    }

    public Vote(User user, Party party) {
        this.user = user;
        this.party = party;
    }

    public String getUserId() {
        return this.user.getId();
    }

//    public void setUserId(User user) {
//        this.user = user;
//    }
//
//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public Integer getPartyId() {
        return this.party.getId();
    }


//    public void setPartyId(Party party) {
//        this.party = party;
//    }
//
//    public Party getParty() {
//        return party;
//    }
//
//    public void setParty(Party party) {
//        this.party = party;
//    }
}
