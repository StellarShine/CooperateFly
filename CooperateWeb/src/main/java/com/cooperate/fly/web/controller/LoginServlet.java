package com.cooperate.fly.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.service.user.UserHelper;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	UserHelper userHelper;
	@Autowired
	User user;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		user=userHelper.loginUser(userName, password);
		
		//�û����������¼����
		if(user==null){
			String message=String.format("�û�������������! <meta http-equiv='refresh' conten='2;url=%s'", request.getContextPath()+"/servlet/LoginUIServlet");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//�û���¼�ɹ��󣬽��û��洢��session��
		
		request.getSession().setAttribute("user", user);
		String message=String.format("��¼�ɹ�:%s !<meta http-equiv='refresh' content='1;url=%s'", user.getUserName(),request.getContextPath()+"/index.jsp");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);				
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
