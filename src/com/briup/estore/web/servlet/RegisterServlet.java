package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.service.impl.CustomerServiceImp;
@WebServlet("/registerS")
public class RegisterServlet extends HttpServlet{
    
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	String name = req.getParameter("name");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String zip = req.getParameter("zip");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		Customer customer = new Customer();
		
		customer.setName(name);
		customer.setPassword(password);
		customer.setZip(zip);
		customer.setAddress(address);
		customer.setTelephone(telephone);
		customer.setEmail(email);
		
		String path = "";
		String msg = "";
		try {
			CustomerServiceImp customerService = new CustomerServiceImp();
			customerService.register(customer);
			msg = "注册成功";
			path = "login.jsp";
			
			
		} catch (Exception e) {
			msg= "注册失败"+e.getMessage();
			path = "register.jsp";
		}
		
		req.getSession().setAttribute("msg", msg);
		resp.sendRedirect(path);
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doGet(req, resp);
    }
}
