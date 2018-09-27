package com.hgd.ebp.service;

import java.sql.Timestamp;
import java.util.List;

import com.hgd.ebp.dao.UserDAO;
import com.hgd.ebp.domain.User;
import com.hgd.ebp.exception.UserException;
import com.hgd.ebp.state.UserSearchState;
import com.hgd.ebp.util.WebUtil;

public class UserService {
	
	private static UserService userService=new UserService();
	
	public static UserService getInstance()
	{
		return userService;
	}
	
	private UserDAO dao;
	
	public UserService()
	{
		dao=UserDAO.getInstance();
	}
	
	public List<User> GetAllUser() throws UserException
	{
		List<User> userList = dao.GetAllUser();
		return userList;
	}
	
	public void AddUser(User user) throws UserException
	{
		dao.AddUser(user);
	}
	
	public User VertifyUser(User user) throws UserException
	{
		return dao.SearchUserByUsername(user);
	}
	
	public List<User> SearchUser(Timestamp bTime,Timestamp eTime) throws UserException
	{
		List<User> userList = dao.SearchUserByTime(bTime, eTime);
		return userList;
	}
	
	public int getLastPage(UserSearchState state) throws UserException
	{
		int count=dao.GetMaxCount(state);
		int maxPage=(count+WebUtil.MAX_LINES-1) / WebUtil.MAX_LINES;//这里之所以这么计算是考虑count=0
		int lastPage=(maxPage>0) ? maxPage-1:0;
		
		return lastPage;
	}
	
	public List<User> SearchUserByPage(UserSearchState state,String page) throws UserException
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
		
		List<User> userList=dao.getUserByPage(state);
		return userList;
	}
}
