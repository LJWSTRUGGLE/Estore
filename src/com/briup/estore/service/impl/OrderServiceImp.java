package com.briup.estore.service.impl;

import java.util.Collection;
import java.util.List;


import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Line;
import com.briup.estore.bean.Order;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.dao.ILineDao;
import com.briup.estore.dao.IOrderDao;
import com.briup.estore.service.IOrderService;

public class OrderServiceImp implements IOrderService{
	private IOrderDao orderDao;
	private ILineDao lineDao;
	
	@Override
	public void deleteOrder(Long id) throws OrderException {
		
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		orderDao = session.getMapper(IOrderDao.class);
		lineDao = session.getMapper(ILineDao.class);
		lineDao.deleteLineByOrderId(id);
		orderDao.deleteOrderById(id);
		
		
		session.commit();
		session.close();
		
	}

	@Override
	public Order findById(Long id) throws OrderException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		orderDao = session.getMapper(IOrderDao.class);
		Order order = orderDao.findOrderById(id);
		return order;
	}

	@Override
	public List<Order> findByCustomerId(Long id) throws OrderException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		orderDao = session.getMapper(IOrderDao.class);
		
		List<Order> list = orderDao.findOrderByCustomerId(id);

		return list;
	}

	@Override
	public void saveOrder(Order order) throws OrderException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		orderDao = session.getMapper(IOrderDao.class);
		lineDao = session.getMapper(ILineDao.class);
		
		try {
			orderDao.saveOrder(order);
			Collection<Line> lines = order.getLines();
			for(Line line:lines){
				line.setOrder(order);
				lineDao.savaLine(line);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new OrderException(e.getMessage());
		}
		
	}

	@Override
	public void updateOrderStatusById(Long curOrderId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		orderDao = sqlSession.getMapper(IOrderDao.class);
		try {
			orderDao.updateOrderStatusById(curOrderId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}
		
	}

}
