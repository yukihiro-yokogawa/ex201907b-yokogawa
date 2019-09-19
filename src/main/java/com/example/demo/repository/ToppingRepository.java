package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.Topping;

/**
 * トッピングに関するDBを操作するクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
@Transactional
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs,i) ->{
		
		Topping topping = new Topping();
		
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		
		return topping;
	};

	/**
	 * 商品のトッピングをすべて検索するメソッドです.
	 * 
	 * @return 全トッピング情報
	 */
	public List<Topping> findAll(){
		
		String sql = "SELECT id,name,price_m,price_l FROM toppings ORDER BY id";
		
		List<Topping> toppingList = template.query(sql,TOPPING_ROW_MAPPER);
		
		return toppingList;
		
	}
}