package com.oth.secuw.repo;

import com.oth.secuw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
}
