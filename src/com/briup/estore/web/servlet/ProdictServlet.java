package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Book;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImp;
@WebServlet("/user/productDetail")
public class ProdictServlet extends HttpServlet{
     private IBookService bookService=new BookServiceImp();
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println(req.getParameter("id"));
    	long id =Long.parseLong(req.getParameter("id"));
    	try {
			Book book = bookService.findById(id);
			req.getSession().setAttribute("b", book);
			resp.sendRedirect(req.getContextPath()+"/user/productDetail.jsp");
		} catch (BookException e) {
			
			e.printStackTrace();
		}
    }
}
