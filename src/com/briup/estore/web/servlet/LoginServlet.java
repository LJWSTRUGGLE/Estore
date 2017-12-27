package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.impl.CustomerServiceImp;
@WebServlet("/loginS")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("userid");
		String password = req.getParameter("password");
		String clientCheckcode = req.getParameter("validateCode");
        String serverCheckcode = (String) req.getSession().getAttribute("checkcode");
		String path = "";
		String msg = "";
		try {
			CustomerServiceImp customerService =new CustomerServiceImp();
			Customer customer = customerService.login(name, password);
			req.getSession().setAttribute("customer", customer);
			if(clientCheckcode.equals(serverCheckcode)){
				msg="登录成功";
				path="/index2.jsp";
			}
			else{
				msg= "验证码错误！";
				path = "/login.jsp";
			}
		} catch (CustomerException e) {
			msg= "登录失败"+e.getMessage();
			path = "/login.jsp";
		}
		req.getSession().setAttribute("msg", msg);
		resp.sendRedirect(req.getContextPath()+path);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
