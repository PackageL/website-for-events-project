package com.sda.repository;

import com.sda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByUsernameAndPassword(String username, String password);
    User findUserByEmail(String email);

   User findUserByUsername(String username);

}
