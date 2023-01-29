package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.Register;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegisterController {
    @Autowired
    private RegisterRepo registerRepo;

    @PostMapping("/register")
    Register newRegUser(@RequestBody Register newUser) {
        return registerRepo.save(newUser);
    }

    @GetMapping("/registers")
    List<Register> getAllRegisteredUsers() {
        return registerRepo.findAll();
    }

    @GetMapping("/register/{id}")
    Register getUserById(@PathVariable Long id) {
        return registerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/register/{id}")
    Register updateUser(@RequestBody Register newUser, @PathVariable Long id) {
        return registerRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setIsAdmin(newUser.getIsAdmin());
                    return registerRepo.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/register/{id}")
    String deleteUser(@PathVariable Long id){
        if(!registerRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        registerRepo.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }


}
