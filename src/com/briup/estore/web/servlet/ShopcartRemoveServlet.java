package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCar;
@WebServlet("/user/shopcartRemove")
public class ShopcartRemoveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	    long id =Long.parseLong( req.getParameter("id"));
    	    ShoppingCar cart = (ShoppingCar)req.getSession().getAttribute("shopcart");
    	    cart.delete(id);
    	    String path = req.getContextPath()+"/user/shopcart.jsp";
    	    resp.sendRedirect(path);
    	    
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doGet(req, resp);
    }
}
