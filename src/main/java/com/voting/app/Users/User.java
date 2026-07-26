package com.voting.app.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @Size(min = 3)
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = 10, max = 12)
    private String phone;

}
