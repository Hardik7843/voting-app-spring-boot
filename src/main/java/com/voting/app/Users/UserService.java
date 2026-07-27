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

    public User createUser(User user) {
        User u = new User(user.getEmail(), user.getName(), user.getPhone(), passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(u);
        return savedUser;
    }

    public User loginUser(User u) {

        User user = repository.findUserByEmail(u.getEmail());

        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        Boolean matchedPass = passwordEncoder.matches(u.getPassword(), user.getPassword());

        if (!matchedPass) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;

    }

}
