package com.hgd.ebp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrderException;
import com.hgd.ebp.service.OrderService;

@SuppressWarnings("serial")
public class OrderSearchServlet extends HttpServlet {

	public OrderSearchServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService orderService=OrderService.getInstance();
		StringBuffer sb = new StringBuffer(request.getParameter("starttime"));
		StringBuffer sb2 = new StringBuffer(request.getParameter("endtime"));
		
		Map<String,String> errMap=new HashMap<String,String>();
		if("".equals(request.getParameter("starttime"))||null==request.getParameter("starttime"))
			errMap.put("starttime", "请输入搜索起始时间！");
		if("".equals(request.getParameter("endtime"))||null==request.getParameter("endtime"))
			errMap.put("endtime", "请输入搜索结束时间！");
		if(0!=errMap.size())
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("OrderManage.jsp");
			rd.forward(request,response);
			return;
		}
		
		String starttime = sb.append(" 00:00:00").toString();
		String endtime = sb2.append(" 00:00:00").toString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		try 
		{
			ts = Timestamp.valueOf(starttime);
			ts2 = Timestamp.valueOf(endtime);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<Order> orderList=orderService.SearchUser(ts, ts2);
			request.setAttribute("orderList", orderList);
			RequestDispatcher rd = request.getRequestDispatcher("OrderManage.jsp");
			rd.forward(request,response);
			return;
		} catch (OrderException e) {
			e.printStackTrace();
			errMap.put("global","出现异常错误，请联系平台管理员。");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("AdminIndex.jsp");
			rd.forward(request,response);
			return;
		}
	}

	public void init() throws ServletException {
	}

}
