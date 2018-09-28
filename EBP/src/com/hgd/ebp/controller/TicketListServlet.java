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
import javax.servlet.http.HttpSession;

import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.exception.TicketException;
import com.hgd.ebp.service.TicketService;
import com.hgd.ebp.state.TicketSearchState;

@SuppressWarnings("serial")
public class TicketListServlet extends HttpServlet {

	public TicketListServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		String page=request.getParameter("page");
		
		TicketSearchState state=null;
		if(null==page)
		{
			page="0";
			session.removeAttribute("TicketSearchState");
			state=new TicketSearchState();
		}
		else
		{
			state=(TicketSearchState)session.getAttribute("TicketSearchState");
			if(null==state)
				state=new TicketSearchState();
		}
		
		TicketService ticketService=TicketService.getInstance();
		try {
			int lastPage=ticketService.getLastPage(state);
			state.setLastPage(lastPage);
			List<Ticket> ticketList=ticketService.SearchTicketByPage(state,page);
			session.setAttribute("TicketSearchState", state);
			request.setAttribute("ticketList", ticketList);
			RequestDispatcher rd = request.getRequestDispatcher("TicketList.jsp");
			rd.forward(request,response);
			return;
		} catch (TicketException e) {
			e.printStackTrace();
			Map<String,String> errMap=new HashMap<String,String>();
			errMap.put("global","出现异常错误，请联系平台管理员。");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("ClientIndex.jsp");
			rd.forward(request,response);
			return;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String page=request.getParameter("page");
		TicketSearchState state=null;
		if(null==page)
			page="0";
		session.removeAttribute("TicketSearchState");
		state=new TicketSearchState();
		
		StringBuffer sb = new StringBuffer(request.getParameter("starttime"));
		String starttime = sb.append(" 00:00:00").toString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		Map<String,String> errMap=new HashMap<String,String>();
		if("".equals(request.getParameter("starttime"))||null==request.getParameter("starttime"))
			errMap.put("starttime", "请输入搜索起始时间！");
		if(0!=errMap.size())
		{
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("TicketList.jsp");
			rd.forward(request,response);
			return;
		}
		try 
		{
			ts = Timestamp.valueOf(starttime);
			System.out.println(ts);
		}catch (Exception e) {
			e.printStackTrace();
		}
		state.setStarttime(ts);
		state.setEndtime(null);
		
		TicketService ticketService=TicketService.getInstance();
		try {
			int lastPage=ticketService.getLastPage(state);
			state.setLastPage(lastPage);
			List<Ticket> ticketList=ticketService.SearchTicketByPage(state,page);
			session.setAttribute("TicketSearchState", state);
			request.setAttribute("ticketList", ticketList);
			RequestDispatcher rd = request.getRequestDispatcher("TicketList.jsp");
			rd.forward(request,response);
			return;
		} catch (TicketException e) {
			e.printStackTrace();
			errMap=new HashMap<String,String>();
			errMap.put("global","出现异常错误，请联系平台管理员。");
			request.setAttribute("errMap", errMap);
			RequestDispatcher rd = request.getRequestDispatcher("ClientIndex.jsp");
			rd.forward(request,response);
			return;
		}
	}

	public void init() throws ServletException {
	}

}
