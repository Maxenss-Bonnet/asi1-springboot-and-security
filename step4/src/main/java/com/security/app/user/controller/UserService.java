package com.security.app.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.app.auth.controller.UserRepository;
import com.security.app.user.model.User;
import com.security.app.user.model.UserDto;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean addUser(UserDto userDto) {
		Optional<User> u =userRepository.findUserByUsername(userDto.getUsername());
		if( !u.isPresent()){
			User u_new=new User();
			u_new.setUsername(userDto.getUsername());
			u_new.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(u_new);
			return true;
		}
		return false;
	}
	
	public boolean setUser(UserDto userDto, String username) {
		Optional<User> uOpt =userRepository.findUserByUsername(username);
		if( uOpt.isPresent()){
			User u=uOpt.get();
			u.setUsername(userDto.getUsername());
			u.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(u);
			return true;
		}
		return false;
	}
	
	public boolean delUser(String username) {
		Optional<User> u =userRepository.findUserByUsername(username);
		if( u.isPresent()){
			userRepository.delete(u.get());
			return true;
		}
		return false;
	}
	
	public User getUser(String username) {
		Optional<User> u =userRepository.findUserByUsername(username);
		if( u.isPresent()){
			return u.get();
		}
		return null;
	}
	
	public User getUserNoPwd(String username) {
		Optional<User> uOpt =userRepository.findUserByUsername(username);
		if( uOpt.isPresent()){
			User u=uOpt.get();
			u.setPassword("*************");
			return u;
		}
		return null;
	}

	public List<User> getAllUserNoPwd() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(s -> {
			s.setPassword("*************");
			userList.add(s);
		});
		return userList;
	}

}
