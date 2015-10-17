package com.cooperate.fly.web.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//移除存储在session中的user对象，实现注销功能
		request.getSession().removeAttribute("user");
		String tempStr1=MessageFormat.format("注销成功！！<meta http-equiv=''refresh'' content=''1;url={0}''/>", request.getContextPath()+"/servlet/LoginUIServlet");
		System.out.println(tempStr1);
		
		String message=String.format("注销成功!<meata http-equiv='refresh' content='1;url=%s'/>", request.getContextPath()+"/servlet/LoginUIServlet");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
