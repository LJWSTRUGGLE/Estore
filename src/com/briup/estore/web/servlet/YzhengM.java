package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YzhengM extends HttpServlet{

	private static final long serialVersionUID = 1L;
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
        	doPost(req, resp);
        }
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
        	resp.setContentType("text/html");
        	String uname = req.getParameter("uname");
        	String pwd = req.getParameter("pwd");
        	String ck = req.getParameter("ck");
        	String clientCheckcode = req.getParameter("validateCode");
            String serverCheckcode = (String) req.getSession().getAttribute("checkcode");
        	if("on".equals(ck)&&clientCheckcode.equals(serverCheckcode)){
        		Cookie c = new Cookie("users",uname+"-"+pwd);
        		c.setMaxAge(600);
        		resp.addCookie(c);
        		System.out.println("验证码验证通过！");
        		String path = "ok.html";
        		resp.sendRedirect(path);
        	}
        	else {
                 System.out.println("验证码验证失败！");
             }
        }
       
}
