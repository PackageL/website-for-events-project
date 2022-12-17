package com.sda.repository;

import com.sda.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {
}
