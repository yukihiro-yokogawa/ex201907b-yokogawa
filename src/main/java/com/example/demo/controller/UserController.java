package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.UserForm;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public UserForm setUpInsertUserForm() {
		return new UserForm(); 
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping("/insertForm")
	public String insertForm() {
		return "register_user";
	}
	
	@RequestMapping("/login")
	public void cartUpdate() {
		System.out.println(1);
	}
	
	@RequestMapping("/insert")
	public String login(@Validated UserForm form, BindingResult rs, Model model) {
		
		if(userService.findByEmail(form.getEmail()) != null) {
			rs.rejectValue("email","", "そのパスワードは既に登録されています。");
			return insertForm();
		}
		
		if(!form.getPassword() .equals(form.getConfirmPassword())) {
			rs.rejectValue("password","","パスワードと確認用パスワードを一致させてください");
			return insertForm();
		}
		
		if(rs.hasErrors()) {
			return insertForm();
		}
		
		userService.insert(form);
		
		return "redirect:/loginForm";
	}
	
	
}
