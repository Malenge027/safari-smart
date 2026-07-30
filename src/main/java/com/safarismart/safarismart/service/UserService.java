package com.safarismart.safarismart.service;

import com.safarismart.safarismart.model.User;
import com.safarismart.safarismart.model.UserRole;
import com.safarismart.safarismart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id: " + id));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findOrCreateUser(String name, String email, String phone) {
        return userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(User.builder()
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .role(UserRole.USER)
                        .build()));
    }

    public User registerAdmin(String name, String email, String phone, String password) {
        return userRepository.save(User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .role(UserRole.ADMIN)
                .build());
    }
}
