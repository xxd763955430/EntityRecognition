package com.java.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter extends HttpServlet implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request=(HttpServletRequest)arg0;       
	     HttpServletResponse response  =(HttpServletResponse)arg1;        
	     HttpSession session = request.getSession(true);   
	     String url=request.getRequestURI();  
	     String username = (String)session.getAttribute("username");
	     if ("/Similarity/servlet/LoginServlet".equals(url)||"/Similarity/servlet/SimServlet".equals(url)
	    		 ||url.contains(".png")) { 
	       chain.doFilter(request, response); 
	       return; 
	     } 
	     if(username!=null) 
	     {
	    	 chain.doFilter(request, response);
	    	 return;
	     }
	     else
	     {
	    	 request.getRequestDispatcher("/login.jsp").forward(request, response); 	    	
	    	 return;
	     }	       		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
