package com.voting.app.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @NotNull(message = "name can't be null")
    @Size(min = 3)
    private String name;

    @Column(unique = true)
    @NotNull(message = "email can't be null")
    @Email
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Vote vote;

    @Column
    @NotNull(message = "password can't be null")
    private String password;

    @Column(unique = true)
    @NotNull(message = "phone number can't be null")
    @Size(min = 10, max = 12)
    private String phone;

    public User() {
    }

    public User(String email, String name, String phone, String password) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
