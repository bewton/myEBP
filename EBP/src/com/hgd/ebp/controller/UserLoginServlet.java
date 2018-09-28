package com.hgd.ebp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgd.ebp.domain.User;
import com.hgd.ebp.exception.UserException;
import com.hgd.ebp.filter.UserLoginFilter;
import com.hgd.ebp.service.UserService;

@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet {

	public UserLoginServlet() {
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
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		Map<String,String> errMap=new HashMap<String,String>();
		if("".equals(user.getUsername())||null==user.getUsername())
			errMap.put("username", "�û�������Ϊ�գ�");
		if("".equals(user.getPassword())||null==user.getPassword())
			errMap.put("password", "���벻��Ϊ�գ�");
		if(errMap.size()!=0)
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
			rd.forward(request,response);	//�˺�����Ϊ��
			return;
		}
		
		UserService userService=UserService.getInstance();
		try {
			User v_user=userService.VertifyUser(user);
			if(v_user!=null)
			{
				HttpSession session = request.getSession();
				session.setAttribute(UserLoginFilter.ATTR_USER, v_user);
				
				RequestDispatcher rd = request.getRequestDispatcher("UserLoginSucc.jsp");
				rd.forward(request,response);	//������ת
				return;
			}
			else
			{
				errMap.put("account", "�˺Ż��������");
				request.setAttribute("errMap", errMap);
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
				rd.forward(request,response);	//�˺��������
				return;
			}
		} catch (UserException e) {
			e.printStackTrace();
			errMap.put("global", "����δ֪��������ϵƽ̨����Ա��");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
			rd.forward(request,response);	//�쳣����
			return;
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(UserLoginFilter.ATTR_USER);
		response.sendRedirect("./");
	}
	
	public void init() throws ServletException {
	}

}
