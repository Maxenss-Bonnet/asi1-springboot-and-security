package com.security.app.user.controller;

import com.security.app.user.model.Role;
import com.security.app.user.model.User;
import com.security.app.user.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUService implements UserDetailsService{
	private final UserRepository uRepo;
	private final PasswordEncoder passwordEncoder;
	public MyUService(UserRepository uRepo,PasswordEncoder passwordEncoder) {
		this.uRepo=uRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	

	
	public boolean addUser(UserDto userDto) {
		Optional<User> u =uRepo.findUserByUsername(userDto.getUsername());
		if( !u.isPresent()){
			User u_new=new User();
			u_new.setUsername(userDto.getUsername());
			u_new.setPassword(passwordEncoder.encode(userDto.getPassword()));
			List<Role> authorities=new ArrayList<>();

			userDto.getRoleList().forEach (e -> authorities.add(new Role(e)));
			u_new.setRoleList(authorities);
			uRepo.save(u_new);
			return true;
		}
		return false;
	}
	
	public User getUser(String username) {
		Optional<User> u =uRepo.findUserByUsername(username);
		if( u.isPresent()){
			return u.get();
		}
		return null;
	}
	
	public User getUserNoPwd(String username) {
		Optional<User> uOpt =uRepo.findUserByUsername(username);
		if( uOpt.isPresent()){
			User u=uOpt.get();
			u.setPassword("*************");
			return u;
		}
		return null;
	}
	
	public List<User> getAllUserNoPwd() {
		List<User> userList = new ArrayList<>();
		uRepo.findAll().forEach(s -> {
			s.setPassword("*************");
			userList.add(s);
		});
		return userList;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = uRepo.findUserByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return user;
	}

}
