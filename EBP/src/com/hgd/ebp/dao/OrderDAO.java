package com.hgd.ebp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrderException;
import com.hgd.ebp.state.OrderSearchState;
import com.hgd.ebp.util.WebUtil;


public class OrderDAO {
	private static final String SELECT_ALL_ORDER="SELECT * FROM v_orderManage";
	private static final String SELECT_ORDER_BY_TIME="SELECT * FROM v_orderManage WHERE committime BETWEEN ? AND ?";
	
	private static final String SELECT_COUNT="SELECT COUNT(*) FROM v_orderManage";
	private static final String SELECT_BY_PAGE="SELECT * FROM v_orderManage";
	
	private DataSource ds;
	
	private static OrderDAO dao=new OrderDAO();
	public static OrderDAO getInstance()
	{
		return dao;
	}
	
	private OrderDAO()
	{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/ebpDS");
		} catch (NamingException e) {
			System.out.print("--------------没找到数据库连接池--------------\n");
		}
	}
	
	public List<Order> GetAllOrder() throws OrderException
	{
		List<Order> orderList = new ArrayList<Order>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_ALL_ORDER);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			while(results.next())
			{
				orderList.add(new Order(results.getInt(1),null,
						results.getTimestamp(2),results.getDouble(3),results.getString(4),
						results.getString(5),results.getString(6)));
			}
		} catch (SQLException e) {
			throw new OrderException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return orderList;
	}
	
	public List<Order> SearchOrder(Timestamp bTime,Timestamp eTime) throws OrderException
	{
		List<Order> orderList=new ArrayList<Order>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_ORDER_BY_TIME);
			stmt.setTimestamp(1,bTime);
			stmt.setTimestamp(2,eTime);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			while(results.next())
			{
				orderList.add(new Order(results.getInt(1),null,
						results.getTimestamp(2),results.getDouble(3),null,
						results.getString(4),null));
			}
		} catch (SQLException e) {
			throw new OrderException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return orderList;
	}
	
	
	
	public int GetMaxCount(OrderSearchState state) throws OrderException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_COUNT,state);
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			results.next();
			return results.getInt(1);
		} catch (SQLException e) {
			System.out.print("--------------没有获取到结果--------------\n");
			throw new OrderException(e);
		}finally
		{
			if(connection!=null)
				try {connection.close();} catch (SQLException e) {}
			if(stmt!=null)
				try {stmt.close();} catch (SQLException e) {}
			if(results!=null)
				try {results.close();} catch (SQLException e) {}
		}
	}
	
	public List<Order> getOrderByPage(OrderSearchState state) throws OrderException
	{
		List<Order> ordertList=new ArrayList<Order>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_BY_PAGE,state);
		sql+=" LIMIT ?, ?";
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
			stmt.setInt(1, state.getCurPage()*WebUtil.MAX_LINES);
			stmt.setInt(2, WebUtil.MAX_LINES);
			System.out.print("---------------------------------"+sql+"\n");
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new OrderException(e);
		}
		
		try {
			while(results.next())
			{
				ordertList.add(new Order(results.getInt(1),null,
						results.getTimestamp(2),results.getDouble(3),results.getString(4),
						results.getString(5),results.getString(6)));
			}
		} catch (SQLException e) {
			throw new OrderException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return ordertList;
	}
	
	public String SqlBuild(String sql,OrderSearchState state)
	{
		Timestamp starttime=state.getStarttime();
		Timestamp endtime=state.getEndtime();
		
		if((!"".equals(starttime)||!"".equals(endtime))&&(starttime!=null||endtime!=null))
		{
			sql+=" WHERE";
			boolean and=false;
			if(!"".equals(starttime)&&starttime!=null)
			{
				sql+=" committime > "+"\""+starttime+"\"";
				and=true;
			}
			if(!"".equals(endtime)&&endtime!=null)
				sql+=(and ? " AND" : "")+" committime < "+"\""+endtime+"\"";
		}
		return sql;
	}
}
