package com.auth.springreact.controller;

import com.auth.springreact.model.User;
import com.auth.springreact.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("auth")
@CrossOrigin("http://localhost:5173")
public class UserController {
    @Autowired
    UserServices userServices;

    @GetMapping("")
    public String test(){
        return "Hello everyone";
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return userServices.registerUser(user);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return userServices.getUsers();
    }
}
