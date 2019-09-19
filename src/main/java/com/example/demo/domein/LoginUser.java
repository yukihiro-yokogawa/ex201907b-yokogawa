package com.example.demo.domein;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User{

	private static final long serialVersionUID = 1L;
	/** ユーザー情報 */
	private final com.example.demo.domein.User user;
	
	public LoginUser(com.example.demo.domein.User user,Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(),user.getPassword(),authorityList);
		this.user = user;
	}
	
	public com.example.demo.domein.User getUser() {
		return user;
	}
}
