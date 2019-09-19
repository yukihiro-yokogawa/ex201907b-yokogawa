package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.OrderItemForm;
import com.example.demo.common.GetUserId;
import com.example.demo.service.OrderService;

/**
 * @author yukihiro.yokogawa
 *
 */
@Controller
@RequestMapping("/shopping")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GetUserId getUserId;
	
	@RequestMapping("/cart")
	public String shoppingCartInsert(OrderItemForm form) {
		
		Integer userId = getUserId.getUserId();
		if(userId != null) {
			orderService.insert(form, userId);
		}
		
		return "cart_list";
	}
	
}
