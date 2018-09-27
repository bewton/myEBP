package com.hgd.ebp.service;

import java.sql.Timestamp;
import java.util.List;

import com.hgd.ebp.dao.OrderDAO;
import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrderException;
import com.hgd.ebp.state.OrderSearchState;
import com.hgd.ebp.util.WebUtil;


public class OrderService {
	private static OrderService orderService=new OrderService();
	
	public static OrderService getInstance()
	{
		return orderService;
	}
	
	private OrderDAO dao;
	
	public OrderService()
	{
		dao=OrderDAO.getInstance();
	}
	
	public List<Order> GetAllOrder() throws OrderException
	{
		List<Order> userList = dao.GetAllOrder();
		return userList;
	}
	
	public List<Order> SearchUser(Timestamp bTime,Timestamp eTime) throws OrderException
	{
		List<Order> orderList = dao.SearchOrder(bTime, eTime);
		return orderList;
	}
	
	public int getLastPage(OrderSearchState state) throws OrderException
	{
		int count=dao.GetMaxCount(state);
		int maxPage=(count+WebUtil.MAX_LINES-1) / WebUtil.MAX_LINES;//这里之所以这么计算是考虑count=0
		int lastPage=(maxPage>0) ? maxPage-1:0;
		
		return lastPage;
	}
	
	public List<Order> SearchOrderByPage(OrderSearchState state,String page) throws OrderException
	{
		int curPage=state.getCurPage();
		switch(page)
		{
			case "0":
				curPage=0;
				break;
			case "prev":
				if(curPage>0)
					curPage--;
				break;
			case "next":
				if(curPage<state.getLastPage())
					curPage++;
				break;
			default:
				curPage=state.getLastPage();
				break;
		}
		state.setCurPage(curPage);
		
		List<Order> orderList=dao.getOrderByPage(state);
		return orderList;
	}
}
