package com.sda.service;

import com.sda.model.User;

public interface UserService {

    void saveUser(User user);

    User findUserByEmail(User user);

    User findUserByUsername(User user);
}
