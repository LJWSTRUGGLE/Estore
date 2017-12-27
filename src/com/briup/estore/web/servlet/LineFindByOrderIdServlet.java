package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Line;
import com.briup.estore.common.exception.LineException;
import com.briup.estore.service.ILineService;
import com.briup.estore.service.impl.LineServiceImp;
@WebServlet("/linefindbyid")
public class LineFindByOrderIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private ILineService iLineService =new LineServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	long id =Long.parseLong(req.getParameter("id"));
    	try {
			Set<Line> set = iLineService.findLinesByOrderId(id);
			req.setAttribute("lineSet", set);
		} catch (LineException e) {
			
			e.printStackTrace();
		}
    	req.getRequestDispatcher("user/orderinfo.jsp").forward(req, resp);
    }
}
