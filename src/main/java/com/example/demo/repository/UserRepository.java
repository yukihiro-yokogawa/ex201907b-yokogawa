package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.User;

/**
 * ユーザー情報を扱うDBを操作するクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
@Transactional
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs,i) ->{
		
		User user = new User();
		
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		
		return user;
		
	};
	
	/**
	 * ユーザー情報を登録するメソッドです.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		
		String sql ="INSERT INTO users (name,email,password,zipcode,address,telephone,role) VALUES (:name,:email,:password,:zipcode,:address,:telephone,:role);";
		
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスからユーザー情報を取り出すメソッドです.
	 * 
	 * @param email
	 * @return ユーザー情報
	 */
	public List<User> findByEmail(String email) {
		String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email=:email;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		
		if(userList.size()==0) {
			return null;
		}
		
		return userList;
	}
	
}
