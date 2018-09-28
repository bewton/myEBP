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

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminException;
import com.hgd.ebp.filter.AdminLoginFilter;
import com.hgd.ebp.service.AdminService;

@SuppressWarnings("serial")
public class AdminLoginServlet extends HttpServlet {

	public AdminLoginServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(AdminLoginFilter.ATTR_ADMIN);
		RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.jsp");
		rd.forward(request,response);	//退出登录
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Admin admin=new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		
		Map<String,String> errMap=new HashMap<String,String>();
		if("".equals(admin.getUsername())||null==admin.getUsername())
			errMap.put("username", "用户名不能为空！");
		if("".equals(admin.getPassword())||null==admin.getPassword())
			errMap.put("password", "密码不能为空！");
		if(errMap.size()!=0)
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.jsp");
			rd.forward(request,response);	//账号密码为空
			return;
		}
		
		AdminService adminService=AdminService.getInstance();
		try {
			Admin v_admin=adminService.VertifyUser(admin);
			if(v_admin!=null)
			{
				HttpSession session = request.getSession();
				session.setAttribute(AdminLoginFilter.ATTR_ADMIN, v_admin);
				
				RequestDispatcher rd = request.getRequestDispatcher("AdminIndex.jsp");
				rd.forward(request,response);	//正常跳转
				return;
			}
			else
			{
				errMap.put("account", "账号或密码错误！");
				request.setAttribute("errMap", errMap);
				RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.jsp");
				rd.forward(request,response);	//账号密码错误
				return;
			}
		} catch (AdminException e) {
			e.printStackTrace();
			errMap.put("global", "发生未知错误，请联系平台管理员。");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.jsp");
			rd.forward(request,response);	//异常错误
			return;
		}
	}

	public void init() throws ServletException {	}

}
