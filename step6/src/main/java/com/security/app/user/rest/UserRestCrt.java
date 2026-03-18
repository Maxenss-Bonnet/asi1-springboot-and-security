package com.security.app.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.user.controller.UserService;
import com.security.app.user.model.User;
import com.security.app.user.model.UserDto;

@RestController
@RequestMapping("/users")
public class UserRestCrt {
	@Autowired
	UserService uService;
	
	@RequestMapping(method=RequestMethod.POST,value="/")
	public boolean addUser(@RequestBody UserDto userDto) {
		return uService.addUser(userDto);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/{username}")
	public User getUser(@PathVariable String username) {
		return uService.getUserNoPwd(username);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/{username}")
	public boolean getUser(@PathVariable String username, @RequestBody UserDto userDto) {
		return uService.setUser(userDto,username);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{username}")
	public boolean delUser(@PathVariable String username) {
		return uService.delUser(username);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public List<User> getAllUser() {
		return uService.getAllUserNoPwd();
	}

}
