package com.sda.service.impl;

import com.sda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements com.sda.service.UserService {

    @Autowired
    private UserRepository userRepository;
}
