package com.taskmgtsys1.taskmanagmentsys.service;

import com.taskmgtsys1.taskmanagmentsys.model.User;
import com.taskmgtsys1.taskmanagmentsys.repisotory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // Add EmailService

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register a new user
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    // Authenticate user
    public boolean authenticate(String email, String password) {
        Optional<User> user = findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    // Generate JWT token for authenticated user
    public String generateJwtToken(String email) {
        return "mock-jwt-token";  // Placeholder, replace with actual token generation logic
    }

    // Get user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get user role by email
    public String getRoleByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getRole();
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }

    // Create password reset token
    public void createPasswordResetToken(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userRepository.save(user);
            emailService.sendPasswordResetEmail(user.getEmail(), token); // Send reset email
        } else {
            throw new IllegalArgumentException("Email address not found");
        }
    }

    // Reset password
    public void resetPassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    // Get user by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Update user details
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setRole(user.getRole());
            return userRepository.save(updatedUser);
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    // Enable/Disable Two-Factor Authentication
    public User toggleTwoFactorAuthentication(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setTwoFactorEnabled(!user.isTwoFactorEnabled());
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("User not found!");
    }

    // Delete user by ID
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found!");
        }
        userRepository.deleteById(id);
    }
}
