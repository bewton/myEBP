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
			errMap.put("username", "�û�������Ϊ�գ�");
		if("".equals(user.getPassword())||null==user.getPassword())
			errMap.put("password", "���벻��Ϊ�գ�");
		if(!password.equals(repassword))
			errMap.put("repassword", "�����������벻һ�£�");
		if("".equals(user.getName())||null==user.getName())
			errMap.put("name", "��ʵ��������Ϊ�գ�");
		if("".equals(user.getGender())||null==user.getGender())
			errMap.put("gender", "�Ա���Ϊ�գ�");
		if("".equals(user.getIdcard())||null==user.getIdcard())
			errMap.put("idcard", "���֤�Ų���Ϊ�գ�");
		if("".equals(age)||null==age)
			errMap.put("age", "���䲻��Ϊ�գ�");
		if("".equals(user.getAddress())||null==user.getAddress())
			errMap.put("address", "��ַ����Ϊ�գ�");
		if("".equals(user.getTelno())||null==user.getTelno())
			errMap.put("telno", "�绰���벻��Ϊ�գ�");
		if(errMap.size()!=0)
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
			rd.forward(request,response);	//ĳ��Ϊ��
			return;
		}
		
		UserService userService=UserService.getInstance();
		try {
			userService.AddUser(user);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegisterSucc.jsp");
			rd.forward(request,response);	//������ת
			return;
		} catch (UserException e) {
			e.printStackTrace();
			errMap.put("global", "����δ֪��������ϵƽ̨����Ա��");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
			rd.forward(request,response);	//�쳣����
			return;
		}
	}

	public void init() throws ServletException {
	}

}
