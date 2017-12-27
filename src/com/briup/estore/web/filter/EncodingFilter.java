package com.briup.estore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
   private String encoding;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse reps,
			FilterChain chain) throws IOException, ServletException {
		    req.setCharacterEncoding(encoding);
		    reps.setCharacterEncoding(encoding);
		    chain.doFilter(req, reps);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		encoding = config.getInitParameter("encoding");
	}

}
