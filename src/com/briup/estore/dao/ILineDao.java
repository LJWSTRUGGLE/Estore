package com.briup.estore.dao;

import java.util.Set;

import com.briup.estore.bean.Line;

public interface ILineDao  {
	public void savaLine(Line line);
	public void deleteLineByOrderId(long orderId);
	public Set<Line> findLinesByOrderId(Long orderId);
}
