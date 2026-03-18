package com.security.app.auth.controller;

import java.util.Optional;

import com.security.app.auth.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User>  findUserByUsername(String username);
}