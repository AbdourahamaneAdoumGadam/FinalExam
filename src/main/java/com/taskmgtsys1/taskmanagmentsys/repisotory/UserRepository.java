package com.taskmgtsys1.taskmanagmentsys.repisotory;

import com.taskmgtsys1.taskmanagmentsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by email (for login or password reset)
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);

    // Check if a user with a given email exists
    boolean existsByEmail(String email);
}
