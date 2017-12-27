package com.briup.estore.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.dao.ICustomerDao;
import com.briup.estore.service.ICustomerService;

public class CustomerServiceImp implements ICustomerService{

	@Override
	public void register(Customer customer) throws CustomerException {
		if(customer==null||StringUtils.isBlank(customer.getName())||StringUtils.isBlank(customer.getPassword())){
			throw new CustomerException("注册信息不存在或者用户名密码为空");
		}
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		ICustomerDao mapper = session.getMapper(ICustomerDao.class);
		if(mapper.findByName(customer.getName())!=null){
			throw new CustomerException("用户名已经存在");
		}
		
		mapper.saveCustomer(customer);
		session.commit();
		session.close();
		
	}

	@Override
	public Customer login(String name, String password)
			throws CustomerException {
		if(StringUtils.isBlank(name)||StringUtils.isBlank(password)){
			throw new CustomerException("用户名密码为空");
		}
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		ICustomerDao mapper = session.getMapper(ICustomerDao.class);
		 Customer customer = mapper.findByName(name);
		if (customer == null) {
			throw new CustomerException("用户名不存在");
		}
		if (!password.equals(customer.getPassword())) {
			throw new CustomerException("密码错误"); 
		}
	
		
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		     ICustomerDao mapper = session.getMapper(ICustomerDao.class);
		     mapper.updateCustomer(customer);
             session.commit();
             session.close();
	}

}
