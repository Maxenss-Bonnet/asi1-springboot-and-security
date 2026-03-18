package com.security.app.user.model;
public class UserDto {
    private String username;

    private String password;

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
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
                .append(role).append("]");
        return builder.toString();
    }

}