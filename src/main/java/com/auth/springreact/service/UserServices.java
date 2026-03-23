package com.auth.springreact.service;

import com.auth.springreact.exception.UserCreationException;
import com.auth.springreact.exception.UserDeletionException;
import com.auth.springreact.exception.UserUpdateException;
import com.auth.springreact.model.User;
import com.auth.springreact.repository.UserRepository;
import com.auth.springreact.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
//      user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userRepository.save(user);
        }catch(Exception e){
            throw new UserCreationException("Failed to create user");
        }
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        return users;
    }

    public User getUserInfo(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, User newUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        try {
            if (newUser.getEmail() != null) {
                user.setEmail(newUser.getEmail());
            } else {
                user.setEmail(user.getEmail());
            }

            if (newUser.getUsername() != null) {
                user.setUsername(newUser.getUsername());
            } else {
                user.setUsername(user.getUsername());
            }

            return userRepository.save(user);
        }catch(Exception e){
            throw new UserUpdateException("Unable to update user with id "+id);
        }
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        try {
            userRepository.deleteById(id);
            return "Deleted successfully";
        }catch(Exception e){
            throw new UserDeletionException("Unable to delete user with id "+id);
        }
    }
}
