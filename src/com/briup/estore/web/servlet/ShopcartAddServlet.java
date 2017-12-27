package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.briup.estore.bean.Book;
import com.briup.estore.bean.Line;
import com.briup.estore.bean.ShoppingCar;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImp;
@WebServlet("/user/shopcartAdd")
public class ShopcartAddServlet extends HttpServlet{
	 private IBookService bookService=new BookServiceImp();
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	long id =Long.parseLong(req.getParameter("id"));
    	try {
			Book book = bookService.findById(id);
			System.out.println(book);
			
		     ShoppingCar cart = (ShoppingCar)req.getSession().getAttribute("shopcart");
		     Line line = new Line();
		     line.setBook(book);
		     cart.add(line);
			req.getSession().setAttribute("book", book);
			resp.sendRedirect(req.getContextPath()+"/user/shopcart.jsp");
		} catch (BookException e) {
			
			e.printStackTrace();
		}
    }
}
