package com.gestion.competence.com.gestion.competence.Controllers;

import com.gestion.competence.com.gestion.competence.Services.UserService;
import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserCrudController {

    private final UserService userService;

    @Autowired
    public UserCrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.save(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
@PostMapping("/create")
public ResponseEntity<User> createUser(@RequestBody User user) {

    if (user.getName() == null || user.getName().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be null or empty");
    }

    User createdUser = userService.save(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
}

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            user.setId(id);
            User updatedUser = userService.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
