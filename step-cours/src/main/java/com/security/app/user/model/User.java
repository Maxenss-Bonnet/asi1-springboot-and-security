package com.security.app.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APPUSER")
public class User implements Serializable,UserDetails{
	private static final long serialVersionUID = 4668602180892212165L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;

	@OneToMany
	(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roleList;


	public User(){

	}
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roleListAuthorities= new ArrayList<>();
		for(Role r: roleList){
			roleListAuthorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		return roleListAuthorities;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
