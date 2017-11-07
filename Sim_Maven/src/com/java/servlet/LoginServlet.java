package com.java.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=URLDecoder.decode(request.getParameter("username").toString(),"UTF-8");
		String password=URLDecoder.decode(request.getParameter("password").toString(),"UTF-8");
		String rememberMe="";
		if(request.getParameter("remember")!=null)
		{
		  rememberMe=URLDecoder.decode(request.getParameter("remember").toString(),"UTF-8");
		}
		String path=request.getContextPath();
		if(username.equals("admin")&&password.equals("admin"))
		{			
			HttpSession session = request.getSession(); 
		    session.setAttribute("username", username);
		    response.sendRedirect(path+"/index.jsp");
	    } else { 
	    	response.sendRedirect(path+"/login.jsp");	  
	    } 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
