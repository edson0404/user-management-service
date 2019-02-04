package com.blackswan.test.controller;

import com.blackswan.test.model.User;
import com.blackswan.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by EdsonvanWyk on 02/02/19.
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("${spring.data.rest.base-path}/user")
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("${spring.data.rest.base-path}/user/{userId}")
    public User findUserByID(@PathVariable Long userId) {
        //TODO: check if present
        return userRepository.findById(userId).get();
    }

    @PostMapping("${spring.data.rest.base-path}/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("${spring.data.rest.base-path}/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(userRequest.getFirstName() != null ? userRequest.getFirstName() : user.getFirstName());
            user.setLastName(userRequest.getLastName() != null ? userRequest.getLastName() : user.getLastName());
            user.setUserName(userRequest.getUserName() != null ? userRequest.getUserName() : user.getUserName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}
