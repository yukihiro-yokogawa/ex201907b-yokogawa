package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.service.UserService;

/**
 * SpringSecurityの機能からユーザー情報を取得するメソッドです
 * 
 * @author yukihiro.yokogawa
 *
 */
@Component
public class GetUserId {

	@Autowired
	private UserService userService;
	
	public Integer getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!(principal .equals("anonymousUser"))) {
			String email = ((UserDetails)principal).getUsername();
			Integer userId = userService.findByEmail(email).get(0).getId();
			return userId;		
		}
		return null;
	}
	
}
