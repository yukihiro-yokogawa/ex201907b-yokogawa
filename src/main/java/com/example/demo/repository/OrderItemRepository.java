package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.OrderItem;

/**
 * ショッピングカートに入る商品に関するDBを操作するリポジトリクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
@Transactional
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ショッピングカートに入る商品を追加するメソッドです.
	 * 
	 * @param orderItem
	 */
	public void insert(OrderItem orderItem) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		
		String sql = "INSERT INTO order_items (item_id,order_id,quantity,size) VALUE (:item_id,:order_id,:quantity,:size)";
		
		template.update(sql,param);
	}
	
}
