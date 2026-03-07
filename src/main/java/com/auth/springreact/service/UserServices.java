package com.auth.springreact.service;

import com.auth.springreact.model.User;
import com.auth.springreact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public ResponseEntity<User> registerUser(User user){
        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User registered = userRepository.save(user);
            return new ResponseEntity<>(registered, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new User(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<User>> getUsers(){
        try{
            return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }
}
