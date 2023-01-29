package com.codewitharjun.fullstackbackend.service;

import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements userService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
