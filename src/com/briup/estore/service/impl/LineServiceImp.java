package com.briup.estore.service.impl;

import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Line;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.LineException;
import com.briup.estore.dao.ILineDao;
import com.briup.estore.service.ILineService;

public class LineServiceImp implements ILineService{
    
	@Override
	public void saveLines(Set<Line> lines) throws LineException {
		
		
	}

	@Override
	public void removeLines(Long orderId) throws LineException {
		
		
	}

	@Override
	public Set<Line> findLinesByOrderId(Long orderId) throws LineException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		ILineDao mapper = session.getMapper(ILineDao.class);
		Set<Line> set = mapper.findLinesByOrderId(orderId);
		if(set!=null){
			return set;
	    }
		return null ;
	}
}
