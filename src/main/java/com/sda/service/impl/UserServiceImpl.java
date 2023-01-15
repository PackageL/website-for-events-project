package com.sda.service.impl;

import com.sda.model.MyUserDetails;
import com.sda.model.Role;
import com.sda.model.User;
import com.sda.repository.RoleRepository;
import com.sda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements com.sda.service.UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(MyUserDetails myUserDetails) {
        User user = new User();
        user.setUsername(myUserDetails.getUsername());
        user.setEmail(myUserDetails.getEmail());
        user.setPassword(passwordEncoder.encode(myUserDetails.getPassword()));
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(MyUserDetails user) {
        return userRepository.findUserByEmail(user.getEmail());
    }

    @Override
    public User findUserByUsername(MyUserDetails user) {
        return userRepository.findUserByUsername(user.getUsername());
    }
    @Override
    public List<MyUserDetails> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    private MyUserDetails convertEntityToDto(User user){
        MyUserDetails userDto = new MyUserDetails();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDto;
    }



}
