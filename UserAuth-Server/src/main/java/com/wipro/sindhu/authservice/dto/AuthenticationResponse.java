package com.wipro.sindhu.authservice.dto;


import com.wipro.sindhu.authservice.enums.UserRole;

// import lombok.Data;

// @Data
public class AuthenticationResponse {
	
	private String jwt;
	private Long userId;
	private UserRole userRole;
	//private String profession; 
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	

}
