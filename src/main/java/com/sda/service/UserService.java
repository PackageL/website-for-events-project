package com.sda.service;

import com.sda.model.MyUserDetails;
import com.sda.model.User;
import java.util.List;


public interface UserService {

    void saveUser(MyUserDetails myUserDetails);

    User findUserByEmail(MyUserDetails user);

    User findUserByUsername(MyUserDetails user);

    List<MyUserDetails> findAllUsers();
}
