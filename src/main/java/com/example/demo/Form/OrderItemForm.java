package com.example.demo.Form;

import java.util.List;

public class OrderItemForm {
	
	String Id;
	
	String itemId;
	
	String orderId;
	
	String quantity;
	
	String size;
	
	String totalPrice;
	
	List<String> topping;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getTopping() {
		return topping;
	}

	public void setTopping(List<String> topping) {
		this.topping = topping;
	}

	@Override
	public String toString() {
		return "OrderItemForm [Id=" + Id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", topping=" + topping + "]";
	}
	
	
	
}
