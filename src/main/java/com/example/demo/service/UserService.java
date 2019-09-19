package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Form.UserForm;
import com.example.demo.domein.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * ユーザー情報を登録します.
	 * パスワードはここでハッシュ化されます.
	 * 
	 * @param user
	 */
	public void insert(UserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
	
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスからユーザー情報を取得します
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
