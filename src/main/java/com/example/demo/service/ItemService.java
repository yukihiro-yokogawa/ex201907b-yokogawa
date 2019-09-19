package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domein.Item;
import com.example.demo.domein.Topping;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ToppingRepository;

/**
 * 商品情報を操作する機能を持つクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 全ての商品のリストを検索するメソッドです.
	 * 
	 * @return 全商品情報リスト
	 */
	public List<Item> findAll(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	/**
	 * 商品idから商品の詳細情報を検索するメソッドです.
	 * 
	 * @param id 商品id
	 * @return 商品の詳細情報
	 */
	public Item findItemById(String id) {
		Item item = itemRepository.findItemById(Integer.parseInt(id));
		return item;
	}
	
	public List<Topping> findAllOfTopping(){
		List<Topping> toppingList = toppingRepository.findAll();
		return toppingList;
	}
	
}
