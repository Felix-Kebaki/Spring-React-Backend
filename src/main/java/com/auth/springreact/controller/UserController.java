package com.auth.springreact.controller;

import com.auth.springreact.model.User;
import com.auth.springreact.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("auth")
@CrossOrigin("http://localhost:5173")
public class UserController {
    @Autowired
    UserServices userServices;

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser=userServices.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userServices.getUsers());
    }

    @GetMapping("userInfo/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.getUserInfo(id));
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userServices.updateUser(id, user));
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.deleteUser(id));
    }
}
