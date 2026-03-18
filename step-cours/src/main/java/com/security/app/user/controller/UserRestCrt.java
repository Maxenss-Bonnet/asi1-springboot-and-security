package com.security.app.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.user.model.User;
import com.security.app.user.model.UserDto;

@RestController
@RequestMapping("/users")
public class UserRestCrt {
	private final MyUService uService;
	
	public UserRestCrt(MyUService uService) {
		this.uService=uService;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/")
	public boolean addUser(@RequestBody UserDto userDto) {
		return uService.addUser(userDto);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/{username}")
	public User getUser(@PathVariable String username) {
		return uService.getUser(username);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public List<User> getAllUser() {
		return uService.getAllUserNoPwd();
	}

}
