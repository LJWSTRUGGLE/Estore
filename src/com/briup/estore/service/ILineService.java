package com.briup.estore.service;

import java.util.Set;

import com.briup.estore.bean.Line;
import com.briup.estore.common.exception.LineException;

public interface ILineService {
    public void saveLines(Set<Line> lines) throws LineException;
	public void removeLines(Long orderId) throws LineException;
	public Set<Line> findLinesByOrderId(Long orderId) throws LineException;
}
