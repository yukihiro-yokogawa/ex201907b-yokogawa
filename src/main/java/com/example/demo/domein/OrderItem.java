package com.example.demo.domein;

import java.util.List;

public class OrderItem {
	
	Integer id;
	
	Integer itemId;
	
	Integer orderId;
	
	Integer quantity;
	
	Character size;
	
	Item item;
	
	List<OrderTopping> orderToppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public int getSubTotal() {
		
		int subTotal = 0;
		int itemPrice = 0;
		int toppingPrice = 0;
		int toppingCount = orderToppingList.size();
		
		if(size == 'M') {
			itemPrice = item.getPriceM();
			toppingPrice = 200*toppingCount;
		}if(size == 'L') {
			itemPrice = item.getPriceL();
			toppingPrice = 300*toppingCount;
		}

		subTotal = (itemPrice + toppingPrice) * quantity;
		return subTotal;
		
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
	
	
	
}
