package com.auth.springreact.service;

import com.auth.springreact.model.User;
import com.auth.springreact.repository.UserRepository;
import com.auth.springreact.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<User> registerUser(User user) {
        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User registered = userRepository.save(user);
            return new ResponseEntity<>(registered, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> updateUser(Long id,User newUser) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        if(newUser.getEmail()!=null){
            user.setEmail(newUser.getEmail());
        }else{
            user.setEmail(user.getEmail());
        }

        if(newUser.getUsername()!=null){
            user.setUsername(newUser.getUsername());
        }else{
            user.setUsername(user.getUsername());
        }

        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
