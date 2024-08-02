package com.hesfintech.check.service.impl;

import com.hesfintech.check.exception.PasswordIsInvalidException;
import com.hesfintech.check.exception.UserAlreadyExistException;
import com.hesfintech.check.exception.UserNotAuthenticatedException;
import com.hesfintech.check.model.User;
import com.hesfintech.check.repository.UserRepository;
import com.hesfintech.check.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("User not authenticated");
        }

        String username = authentication.getName();

        return userRepository.findByLogin(username)
                .orElseThrow(() -> new com.hesfintech.check.exception.UsernameNotFoundException("User not found"));
    }

    @Override
    public User create(User user) {
        if (exist(user)) {
            throw new UserAlreadyExistException("User " + user.getLogin() + " already exist");
        }
        return userRepository.save(user);
    }

    @Override
    public void changePassword(User user, String currentPassword, String newPassword) {
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new PasswordIsInvalidException("Invalid current password");
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User with login " + login + " not found"));
        return user;
    }

    private boolean exist(User user) {
        String login = user.getLogin();

        return userRepository.existsByLogin(login);
    }
}
