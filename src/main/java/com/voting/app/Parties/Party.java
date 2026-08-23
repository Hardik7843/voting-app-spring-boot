package com.voting.app.Parties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voting.app.Votes.Vote;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "parties")
public class Party {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull(message = "party name can't be null")
    @Size(min = 2)
    private String name;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Vote> votes = new ArrayList<>();

    @Column(unique = true)
    @Size(min = 2)
    @NotNull(message = "party code can't be null")
    private String code;

    public Party(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Party() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
