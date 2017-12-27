package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCar;
@WebServlet("/user/shopcartUpdate")
public class ShopcartUpdateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	       int num = Integer.parseInt(req.getParameter("num"));
    	       long id =Long.parseLong(req.getParameter("id"));
    	       ShoppingCar shoppingCar =(ShoppingCar)req.getSession().getAttribute("shopcart");
    	       shoppingCar.update(id, num);
    	       String path = req.getContextPath()+"/user/shopcart.jsp";
    	       resp.sendRedirect(path);     
    }
 
   
}
