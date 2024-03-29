package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domein.Item;

/**
 * 商品情報のDBを操作するクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
	/**
	 * 全ての商品の情報を検索するメソッドです.
	 * 
	 * @return 商品情報
	 */
	public List<Item> findAll(){
		
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE deleted=false ORDER BY id;";
		
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		
		return itemList;
		
	}
	
	public Item findItemById(Integer id) {
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE deleted=false AND id=:id;";
		
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		
		return item;
		
	}
	
}
