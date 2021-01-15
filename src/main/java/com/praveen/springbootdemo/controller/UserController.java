package com.praveen.springbootdemo.controller;

import com.praveen.springbootdemo.entity.User;
import com.praveen.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public User findByName(@PathVariable String name){
      User user= userRepository.findByName(name);
      return user;
    }
    @GetMapping(value ="/id/{id}")
    public User getUserById(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    @PostMapping(value = "/create")
    public User createUser(@RequestBody final User user){
        userRepository.save(user);
        return userRepository.findByName(user.getName());
    }
}
