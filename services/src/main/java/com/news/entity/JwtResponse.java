package com.news.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String Token;
	private String email;
	private String type="Bearer";
	private Collection<? extends GrantedAuthority> authorities;
	public JwtResponse(String accessToken, String username2, Collection<? extends GrantedAuthority> authorities) {
		this.Token=accessToken;
		this.email=username2;
		this.authorities=authorities;
	}
	
	public String getAccessToken() {
		return Token;
	}
	public void setAccessToken(String accessToken) {
		this.Token = accessToken;
	}
	public String getUserName() {
		return email;
	}
	public void setUserName(String userName) {
		this.email = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthotities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
