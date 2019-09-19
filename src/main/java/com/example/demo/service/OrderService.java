package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Form.OrderItemForm;
import com.example.demo.domein.Order;
import com.example.demo.domein.OrderItem;
import com.example.demo.domein.OrderTopping;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderToppingRepository;

/**
 * ショッピングカートに関する情報を操作するサービスクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * ショッピングカートに商品を追加するクラスです.
	 * 
	 * @param orderItem
	 * @param orderTopping
	 */
	public void insert(OrderItemForm form, Integer userId) {
		Integer orderItemId = null;
		if (orderRepository.findByStatus0(userId) == null) {
			orderRepository.insertOfStatus0(userId);
		}

		Integer orderId = orderRepository.findByStatus0(userId).get(0).getId();
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		orderItem.setSize(form.getSize().charAt(0));
		if (orderItem != null) {
			orderItemId= orderItemRepository.insert(orderItem);
		}

		if (form.getTopping() != null) {
			OrderTopping orderTopping = new OrderTopping();
			for (String toppingId : form.getTopping()) {
				orderTopping.setOrderItemId(orderItemId);
				orderTopping.setToppingId(Integer.parseInt(toppingId));
				if (orderTopping != null) {
					orderToppingRepository.insert(orderTopping);
				}
			}
		}
	}
	
	public List<Order> findByStatus0(Integer userId){
		
		List<Order> orderList = orderRepository.findByStatus0(userId);
		return orderList;
	}

}
