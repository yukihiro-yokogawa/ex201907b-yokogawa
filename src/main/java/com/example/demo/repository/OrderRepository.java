package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.Item;
import com.example.demo.domein.Order;
import com.example.demo.domein.OrderItem;
import com.example.demo.domein.OrderTopping;
import com.example.demo.domein.Topping;

/**
 * ショッピングカートを操作するリポジトリ.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
@Transactional
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTER = (rs) -> {
		
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		OrderTopping orderTopping = new OrderTopping();
		
		int orderIdBefore = -1;
		int orderItemIdBefore = -1;
		int orderToppingIdBefore=-1;
		
		while(rs.next()){
			
			int orderIdNow = rs.getInt("ord_id");
			
			if(orderIdBefore != orderIdNow) {
			
				Order order = new Order();
				order.setId(rs.getInt("ord_id"));
				order.setUserId(rs.getInt("ord_user_id"));
				order.setStatus(rs.getInt("ord_status"));
				order.setTotalPrice(rs.getInt("ord_total_price"));
				order.setOrderDate(rs.getDate("ord_order_date"));
				order.setDestinationEmail(rs.getString("ord_email"));
				order.setDestinationZipcode(rs.getString("ord_zipcode"));
				order.setDestinationAddress(rs.getString("ord_address"));
				order.setDestinationTel(rs.getString("ord_tel"));
				order.setDeliveryTime(rs.getTimestamp("ord_dev_time"));
				order.setPaymentMethod(rs.getInt("ord_payment_method"));

				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			
			orderIdBefore = orderIdNow;
			int orderItemIdNow = rs.getInt("ord_i_id");
			if(orderItemIdBefore != orderItemIdNow) {
				//order_itemsテーブルから取り出す
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("ord_i_id"));
				orderItem.setItemId(rs.getInt("ord_i_item_id"));
				orderItem.setOrderId(rs.getInt("ord_i_order_id"));
				orderItem.setQuantity(rs.getInt("ord_i_quantity"));
				String size = rs.getString("ord_i_size");
				if(size != null) {
					orderItem.setSize(size.charAt(0));
				}
			
				//itemsテーブルから取り出す
				Item item = new Item();
				item.setId(rs.getInt("itm_id"));
				item.setName(rs.getString("itm_name"));
				item.setPriceM(rs.getInt("itm_price_m"));
				item.setPriceL(rs.getInt("itm_price_l"));
				item.setImagePath(rs.getString("itm_image_path"));
				//orderItemオブジェクトに商品情報をセット
				orderItem.setItem(item);
			
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);			
				
				orderItemList.add(orderItem);
			}
			
			orderItemIdBefore = orderItemIdNow;
			int orderToppingIdNow = rs.getInt("ord_t_id");
			if(orderToppingIdBefore != orderToppingIdNow) {
			orderTopping = new OrderTopping();
			orderTopping.setId(rs.getInt("ord_t_id"));
			orderTopping.setToppingId(rs.getInt("ord_t_topping_id"));
			orderTopping.setOrderItemId(rs.getInt("ord_t_order_item_id"));
			
			Topping topping = new Topping();
			topping.setId(rs.getInt("top_id"));
			topping.setName(rs.getString("top_name"));
			topping.setPriceM(rs.getInt("top_price_m"));
			topping.setPriceL(rs.getInt("top_price_l"));
			orderTopping.setTopping(topping);
			
			orderToppingList.add(orderTopping);
			}
			orderToppingIdBefore = orderToppingIdNow;
			
		}
		return orderList;
	};
	
	/**
	 * ショッピングカートを作成するメソッドです.
	 * 初めてショッピングカートを開いたときは空のショッピングカートを作成します.
	 * 
	 * @param orderItem
	 */
	public void insertOfStatus0(Integer userId) {
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id",userId);
		
		String sql = "INSERT INTO orders (user_id,status,total_price) VALUES (:user_id,0,0);";
		
		template.update(sql, param);
	}
	
	/**
	 * 購入前のショッピングカートを検索するメソッドです.
	 * 
	 * @return
	 */
	public List<Order> findByStatus0(Integer userId){
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		
		String sql="SELECT"
				+ " ord.id AS ord_id,"
				+ " ord.user_id AS ord_user_id,"
				+ " ord.status AS ord_status,"
				+ " ord.total_price AS ord_total_price,"
				+ " ord.order_date AS ord_order_date,"
				+ " ord.destination_email AS ord_email,"
				+ " ord.destination_zipcode AS ord_zipcode,"
				+ " ord.destination_address AS ord_address,"
				+ " ord.destination_tel AS ord_tel,"
				+ " ord.delivery_time AS ord_dev_time,"
				+ " ord.payment_method AS ord_payment_method,"
				+ " ord_i.id AS ord_i_id,"
				+ " ord_i.item_id AS ord_i_item_id,"
				+ " ord_i.order_id AS ord_i_order_id,"
				+ " ord_i.quantity AS ord_i_quantity,"
				+ " ord_i.size AS ord_i_size,"
				+ " ord_t.id AS ord_t_id,"
				+ " ord_t.topping_id AS ord_t_topping_id,"
				+ " ord_t.order_item_id AS ord_t_order_item_id,"
				+ " itm.id AS itm_id,"
				+ " itm.name AS itm_name,"
				+ " itm.price_m AS itm_price_m,"
				+ " itm.price_l AS itm_price_l,"
				+ " itm.image_path AS itm_image_path,"
				+ " top.id AS top_id,"
				+ " top.name AS top_name,"
				+ " top.price_m AS top_price_m,"
				+ " top.price_l AS top_price_l"
				+ " FROM"
				+ " orders AS ord"
				+ " FULL OUTER JOIN"
				+ " order_items AS ord_i"
				+ " ON"
				+ " ord.id=ord_i.order_id"
				+ " FULL OUTER JOIN"
				+ " order_toppings AS ord_t"
				+ " ON"
				+ " ord_i.id=ord_t.order_item_id"
				+ " LEFT OUTER JOIN"
				+ " items AS itm"
				+ " ON"
				+ " ord_i.item_id=itm.id"
				+ " LEFT OUTER JOIN"
				+ " toppings AS top"
				+ " ON"
				+ " ord_t.topping_id=top.id"
				+ " WHERE"
				+ " status=0 and user_id=:userId;";
		
		List<Order> orderList = template.query(sql,param,ORDER_RESULT_SET_EXTRACTER);
		
		if(orderList.size() == 0) {
			return null;
		}
		return orderList;
	}
	
	/**
	 * ショッピングカートの中身を削除するメソッドです.
	 * 
	 * @param id
	 */
	public void deleteOfItemInCart(Integer id) {
		String sql ="WITH deleted AS (DELETE FROM order_items WHERE id=:id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT id FROM deleted);";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

		template.update(sql, param);
	}

}
