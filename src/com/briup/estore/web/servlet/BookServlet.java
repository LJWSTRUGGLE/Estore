package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Book;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.impl.BookServiceImp;
@WebServlet("/bookservlet")
public class BookServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	
    	BookServiceImp booksImp = new BookServiceImp();
    	   String msg = "";
    	try {
			List<Book> books = booksImp.listAllBooks();
			req.getSession().setAttribute("books", books);
			resp.sendRedirect("index2.jsp");
		} catch (BookException e) {
			msg="库空"+e.getMessage();
			resp.sendRedirect("index2.jsp");
		}
    	req.getSession().setAttribute("msg", msg);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doGet(req, resp);
    }
}
