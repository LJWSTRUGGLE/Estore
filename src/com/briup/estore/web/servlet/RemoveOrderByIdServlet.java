package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderServiceImp;
@WebServlet("/user/removeOrder")
public class RemoveOrderByIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	long id =Long.parseLong(req.getParameter("id"));
    	String path="";
    	String msg="";
    	try {
			orderService.deleteOrder(id);
			msg="删除成功";
			path = "/user/orderFindAll";
		} catch (OrderException e) {
			e.printStackTrace();
		}
    	req.getSession().setAttribute("msg", msg);
		
		resp.sendRedirect(req.getContextPath()+path);
    }
}
