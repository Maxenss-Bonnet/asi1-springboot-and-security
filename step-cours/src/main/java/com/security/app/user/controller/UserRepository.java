package com.security.app.user.controller;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.security.app.user.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User>  findUserByUsername(String username);
}
