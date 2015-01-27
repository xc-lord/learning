package com.lord.model.dto;

import com.lord.model.User;

public class UserDTO {
	private Long id;
	private String userName;
	private String level;
	private String email;
	private String mobile;
	
	public UserDTO(User user) {
		this.email = user.getEmail();
		this.id = user.getId();
		this.level = user.getLevel().toString();
		this.userName = user.getUserName();
		this.mobile = user.getMobile();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
