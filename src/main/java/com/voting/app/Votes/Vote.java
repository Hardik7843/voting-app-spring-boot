package com.voting.app.Votes;

import jakarta.persistence.*;

@Entity(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String userId;

    @Column
    private Integer partyId;

    public Vote() {
    }

    public Vote(String userId, Integer partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

}
