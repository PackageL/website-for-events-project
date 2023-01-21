package com.sda.service.impl;

import com.sda.model.Role;
import com.sda.model.User;
import com.sda.repository.UserRepository;
import com.sda.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sda.utils.Constants.Security.SECURITY_DEFAULT_ROLE;
import static com.sda.utils.Constants.Security.SECURITY_ROLE_USER;

@Service
public class UserServiceImpl implements com.sda.service.UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public void saveUser(User user) throws Exception {
        user.setRole(roleService.findRoleByName(SECURITY_ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateRole(String username, String  newRole) throws Exception {
        User user = userRepository.findUserByUsername(username);
        user.setRole(roleService.findRoleByName(newRole));
        userRepository.save(user);
    }
}
