package com.voting.app.Repositories;

import com.voting.app.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);

    Optional<User> findUserByEmail(String email);
}
