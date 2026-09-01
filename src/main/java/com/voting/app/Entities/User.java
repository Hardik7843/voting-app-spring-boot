package com.voting.app.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @NotNull(message = "name can't be null")
    @Size(min = 3)
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull(message = "email can't be null")
    @Email
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Vote vote;

    @Column(nullable = false)
    @NotNull(message = "password can't be null")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Role cannot be null")
    private UserRoles role;

    public User() {
    }

    public User(String email, String name, UserRoles role, String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public UserRoles getRole() {
//        return role;
//    }
//
//    public void setRole(UserRoles role) {
//        this.role = role;
//    }
//
//    public Vote getVote() {
//        return vote;
//    }
//
//    public void setVote(Vote vote) {
//        this.vote = vote;
//    }

    public enum UserRoles {
        ADMIN, USER
    }
}
