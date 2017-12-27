package com.briup.estore.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.briup.estore.bean.Book;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImp;
@WebListener
public class ApplicationListen implements ServletContextListener{
        private IBookService bookService=new BookServiceImp();
	
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		      List<Book> list;
			try {
				list = bookService.listAllBooks();
				ServletContext application = sce.getServletContext();
				application.setAttribute("books", list);
			} catch (BookException e) {
				
				e.printStackTrace();
			}
		
		
	}

}
