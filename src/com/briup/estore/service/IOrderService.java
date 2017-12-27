package com.briup.estore.service;

import java.util.List;

import com.briup.estore.bean.Order;
import com.briup.estore.common.exception.OrderException;

public interface IOrderService {
	void saveOrder(Order order) throws OrderException;
	void deleteOrder(Long id) throws OrderException;
	Order findById(Long id) throws OrderException;
	List<Order> findByCustomerId(Long id) throws OrderException;
	void updateOrderStatusById(Long curOrderId);
	
}
