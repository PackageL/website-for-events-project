package com.sda.service;

import com.sda.model.User;

import java.util.List;


public interface UserService {

    void saveUser(User user) throws Exception;

    User findUserByUsername(String username);

    List<User> findAllUsers();
}
