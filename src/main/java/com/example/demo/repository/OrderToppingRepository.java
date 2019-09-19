package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.OrderTopping;

/**
 * ショッピングカートに追加するトッピングに関するDBを操作するリポジトリクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
@Transactional
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ショッピングカートに追加する商品のトッピング情報を追加するクラスです.
	 * 
	 * @param orderTopping
	 */
	public void insert(OrderTopping orderTopping) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		
		String sql = "INSERT INTO order_toppings (topping_id,order_item_id) VALUE (:topping_id,:order_item_id);";
	
		template.update(sql, param);
	}
	
}
