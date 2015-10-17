package com.cooperate.fly.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.service.user.UserHelper;
import com.mysql.jdbc.StreamingNotifiable;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserHelper userService;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId=Integer.parseInt(request.getParameter("id"));
		User user=this.userService.getUserById(userId);
		model.addAttribute("user",user);
		return "showUser";		
	}

}
