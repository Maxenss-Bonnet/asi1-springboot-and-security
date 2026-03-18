package com.security.app.user.model;

import java.util.List;

public class UserDto {
    private String username;

    private String password;

    private List<String> roleList;

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [username=")
                .append(username)
                .append(", role=")
                .append(getRoleList()).append("]");
        return builder.toString();
    }

}