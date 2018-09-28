package com.hgd.ebp.service;

import java.util.List;

import com.hgd.ebp.dao.TicketDAO;
import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.exception.TicketException;
import com.hgd.ebp.state.TicketSearchState;
import com.hgd.ebp.util.WebUtil;


public class TicketService {
	private static TicketService ticketService=new TicketService();
	
	public static TicketService getInstance()
	{
		return ticketService;
	}
	
	private TicketDAO dao;
	
	public TicketService()
	{
		dao=TicketDAO.getInstance();
	}
	
	public int getLastPage(TicketSearchState state) throws TicketException
	{
		int count=dao.GetMaxCount(state);
		int maxPage=(count+WebUtil.MAX_LINES-1) / WebUtil.MAX_LINES;//这里之所以这么计算是考虑count=0
		int lastPage=(maxPage>0) ? maxPage-1:0;
		
		return lastPage;
	}
	
	public List<Ticket> SearchTicketByPage(TicketSearchState state,String page) throws TicketException
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
		
		List<Ticket> ticketList=dao.getTicketByPage(state);
		return ticketList;
	}
}
