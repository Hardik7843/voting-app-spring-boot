package com.voting.app.Services;

import com.voting.app.Entities.User;
import com.voting.app.Exceptions.ResourceConflict;
import com.voting.app.Exceptions.ResourceNotFound;
import com.voting.app.Repositories.UserRepository;
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
        User user = repository.findUserById(id);
        if (user == null) {
            throw new ResourceNotFound("User not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {

        User user = repository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFound("User not found")
        );

        return user;
    }

    public User createUser(User user) {
        User existingUser = repository.findUserByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceConflict("User with email already exists"));


        User u = new User(user.getEmail(), user.getName(), user.getRole(), passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(u);
        return savedUser;
    }

    public User loginUser(User u) {

        User user = repository.findUserByEmail(u.getEmail()).orElseThrow(
                () -> new RuntimeException("Invalid email or password"));


        Boolean matchedPass = passwordEncoder.matches(u.getPassword(), user.getPassword());

        if (!matchedPass) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;

    }

}
