package com.example.demo.domein;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 
 * 注文状況を扱うドメインクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
public class Order {
	
	Integer id;
	
	Integer userId;
	
	Integer status;
	
	Integer totalPrice;
	
	Date orderDate;
	
	String destinationName;
	
	String destinationEmail;

	String destinationZipcode;
	
	String destinationAddress;
	
	String destinationTel;
	
	String destinationTime;
	
	Timestamp deliveryTime;
	
	Integer paymentMethod;
	
	User user;
	
	List<OrderItem> orderItemList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(String destinationTime) {
		this.destinationTime = destinationTime;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public int getCalcTotalPrice() {
		int totalPrice = 0;
		for(OrderItem orderItem : orderItemList) {
			totalPrice = totalPrice + orderItem.getSubTotal();
		}
		totalPrice = (int)(totalPrice * 1.08);
		return totalPrice;
	}
	
	public int getTax() {
		int tax = 0;
		int taxTotalPrice = 0;
		for (OrderItem orderItem : orderItemList){
			taxTotalPrice = taxTotalPrice + orderItem.getSubTotal();
		}
		tax = (int)(taxTotalPrice * 0.08);
		return tax;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", destinationTime=" + destinationTime
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", user=" + user
				+ ", orderItemList=" + orderItemList + "]";
	}
	
	
	
}
