package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domein.Item;
import com.example.demo.domein.Topping;
import com.example.demo.service.ItemService;

/**
 * 商品についてのViewを操作するコントローラークラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Controller
@RequestMapping("")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 商品一覧のViewを表示させるメソッドです.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/top")
	public String ItemList(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList",itemList);
		return "item_list";
	}
	
	/**
	 * 商品詳細のViewを表示させるメソッドです.
	 * 
	 * @param id 商品id
	 * @param model リクエストスコープ
	 * @return 商品詳細
	 */
	@RequestMapping("/itemDetail")
	public String ItemDetail(String id,Model model) {
		Item item = itemService.findItemById(id);
		List<Topping> toppingList = itemService.findAllOfTopping();
		
		model.addAttribute("itemDetail",item);
		model.addAttribute("toppingList",toppingList);
		
		return "item_Detail";
	}
}
