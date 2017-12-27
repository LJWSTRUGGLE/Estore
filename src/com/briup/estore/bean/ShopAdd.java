package com.briup.estore.bean;

public class ShopAdd {
  private Long id;
  private String name;
  private Double price;
  private int num;
  private Double cost;
  private int customer_id;
  
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public Double getCost() {
	return cost;
}
public void setCost(Double cost) {
	this.cost = cost;
}
public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public ShopAdd(Long id, String name, Double price, int num, Double cost,
		int customer_id) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.num = num;
	this.cost = cost;
	this.customer_id = customer_id;
}
@Override
public String toString() {
	return "ShoppingAdd [id=" + id + ", name=" + name + ", price=" + price
			+ ", num=" + num + ", cost=" + cost + ", customer_id="
			+ customer_id + "]";
}
  
}
