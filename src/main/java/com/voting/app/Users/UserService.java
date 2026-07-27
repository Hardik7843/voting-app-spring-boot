package com.voting.app.Users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String id) {
        return repository.findUserById(id);
    }

    public User createUser(String name, String phone, String password, String email) {
        User u = new User(email, name, phone, passwordEncoder.encode(password));

        User savedUser = repository.save(u);
        return savedUser;
    }

}
