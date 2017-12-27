package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImp;

@WebServlet("/user/customerUpdate")
public class CustomerUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ICustomerService iCustomerService = new CustomerServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	long id = Long.parseLong(req.getParameter("id"));
    	String name =req.getParameter("name");
    	String password = req.getParameter("password");
		String address = req.getParameter("address");
		String zip = req.getParameter("zip");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		Customer c = new Customer(id,name,password,address,zip,telephone,email);
		
		String path = "/user/userinfo.jsp";
		String msg = "";
		try {
			iCustomerService.updateCustomer(c);
			msg="修改成功";
			req.getSession().setAttribute("customer", c);
		} catch (CustomerException e) {
			e.printStackTrace();
			msg="修改失败"+e.getMessage();
		}
		req.getSession().getAttribute(msg);
		resp.sendRedirect(req.getContextPath()+path);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	 doGet(req, resp);
    }
}
