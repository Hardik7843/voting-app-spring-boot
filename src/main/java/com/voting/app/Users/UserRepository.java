package com.voting.app.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);

    User findUserByEmail(String email);
}
