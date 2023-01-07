package com.sda.service.impl;

import com.sda.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements com.sda.service.UserService {

    private final UserService userRepository;

    @Autowired
    public UserServiceImpl(UserService userRepository) {
        this.userRepository = userRepository;
    }
}
