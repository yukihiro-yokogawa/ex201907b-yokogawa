package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Form.OrderItemForm;
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
	public void insert(OrderItemForm form,Integer userId) {
		
		if(orderRepository.findByStatus0(userId) == null) {
			orderRepository.insertOfStatus0(userId);
		}
		
		Integer orderId = orderRepository.findByStatus0(userId).get(0).getId();
		OrderItem orderItem = new OrderItem();
		System.out.println(form);
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		orderItem.setSize(form.getSize().charAt(0));
		System.out.println(orderItem);
		if(orderItem != null) {
			orderItemRepository.insert(orderItem);
		}
		
		OrderTopping orderTopping = new OrderTopping();
		for(String toppingId : form.getTopping()) {
			System.out.println(toppingId);
		}
//		if(orderTopping != null) {
//			orderToppingRepository.insert(orderTopping);
//		}
		
	}
	
}
