package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.OrderItemForm;
import com.example.demo.common.GetUserId;
import com.example.demo.domein.Order;
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
	public String shoppingCartInsert(OrderItemForm form,HttpSession session) {	
		//未ログイン時、nullが返ってきている
		Integer userId = getUserId.getUserId();
		String compareToken = String.valueOf(session.getAttribute("provisionalId"));
		if("null".equals (compareToken)) {
			Random random = new Random();
			Integer provisionalId = random.nextInt(2147483646)-2147483647;
			userId = provisionalId;
			session.setAttribute("provisionalId", provisionalId);
		}else if(compareToken .equals (String.valueOf(session.getAttribute("provisionalId")))) {
			userId = Integer.parseInt(String.valueOf(session.getAttribute("provisionalId")));
		}
		
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
	public String showCart(Model model,HttpSession session) {
		//未ログイン時、nullが返ってきている
		Integer userId = getUserId.getUserId();
		String compareToken = String.valueOf(session.getAttribute("provisionalId"));
		System.out.println(userId);
		System.out.println(compareToken);
		if(userId == null && "null".equals (compareToken)) {
			Random random = new Random();
			Integer provisionalId = random.nextInt(2147483646)-2147483647;
			userId = provisionalId;
			session.setAttribute("provisionalId", provisionalId);
		}else if(userId == null && compareToken .equals (String.valueOf(session.getAttribute("provisionalId")))) {
			userId = Integer.parseInt(String.valueOf(session.getAttribute("provisionalId")));
		}else if(userId != null && compareToken .equals (String.valueOf(session.getAttribute("provisionalId")))) {
			if(orderService.findByStatus0(userId)==null) {
				orderService.update(userId,Integer.parseInt(compareToken));
			}
		}
		
		if(orderService.findByStatus0(userId) == null) {
			orderService.insert(userId);
		}
		List<Order> orderList = orderService.findByStatus0(userId);
		model.addAttribute("orderList",orderList);
		return "cart_list";
	}
	
	/**
	 * ショッピングカートの商品を削除するメソッドです.
	 * 
	 * @param id
	 * @return ショッピングカート
	 */
	@RequestMapping("/itemDelete")
	public String itemDelete(String id) {
		orderService.deletOfItemInCart(id);
		return "redirect:/shopping/showCart";
	}
}