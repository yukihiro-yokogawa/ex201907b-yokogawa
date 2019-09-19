package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.OrderItemForm;
import com.example.demo.common.GetUserId;
import com.example.demo.domein.Order;
import com.example.demo.domein.OrderItem;
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
	

	/**
	 * ショッピングカートに商品を追加するメソッドです.
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/insertCart")
	public String shoppingCartInsert(OrderItemForm form) {
		
		Integer userId = getUserId.getUserId();
		if(userId != null) {
			orderService.insert(form, userId);
		}
		
		return "redirect:/shopping/showCart";
	}
	
	/**
	 * ショッピングカートを表示するメソッドです.
	 * 
	 * @return ショッピングカート
	 */
	@RequestMapping("/showCart")
	public String showCart(Model model) {
		Integer userId = getUserId.getUserId();
		List<Order> orderList = orderService.findByStatus0(userId);
		List<OrderItem> orderItemList = orderService.findByStatus0(userId).get(0).getOrderItemList();
		System.out.println(orderItemList);
		model.addAttribute("orderList",orderList);
		return "cart_list";
	}
}