package com.hgd.ebp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgd.ebp.domain.User;
import com.hgd.ebp.exception.UserException;
import com.hgd.ebp.service.UserService;

@SuppressWarnings("serial")
public class UserRegisterServlet extends HttpServlet {

	public UserRegisterServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String idcard=request.getParameter("idcard");
		String age=request.getParameter("age");
		String address=request.getParameter("address");
		String telno=request.getParameter("telno");
		
		User user=new User(0,username,password,name,gender,idcard
				,address,telno,null,0.0,0);
		
		Map<String,String> errMap=new HashMap<String,String>();
		if("".equals(user.getUsername())||null==user.getUsername())
			errMap.put("username", "用户名不能为空！");
		if("".equals(user.getPassword())||null==user.getPassword())
			errMap.put("password", "密码不能为空！");
		if(!password.equals(repassword))
			errMap.put("repassword", "两次输入密码不一致！");
		if("".equals(user.getName())||null==user.getName())
			errMap.put("name", "真实姓名不能为空！");
		if("".equals(user.getGender())||null==user.getGender())
			errMap.put("gender", "性别不能为空！");
		if("".equals(user.getIdcard())||null==user.getIdcard())
			errMap.put("idcard", "身份证号不能为空！");
		if("".equals(age)||null==age)
			errMap.put("age", "年龄不能为空！");
		if("".equals(user.getAddress())||null==user.getAddress())
			errMap.put("address", "地址不能为空！");
		if("".equals(user.getTelno())||null==user.getTelno())
			errMap.put("telno", "电话号码不能为空！");
		if(errMap.size()!=0)
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
			rd.forward(request,response);	//某项为空
			return;
		}
		
		UserService userService=UserService.getInstance();
		try {
			userService.AddUser(user);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegisterSucc.jsp");
			rd.forward(request,response);	//正常跳转
			return;
		} catch (UserException e) {
			e.printStackTrace();
			errMap.put("global", "发生未知错误，请联系平台管理员。");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
			rd.forward(request,response);	//异常错误
			return;
		}
	}

	public void init() throws ServletException {
	}

}
