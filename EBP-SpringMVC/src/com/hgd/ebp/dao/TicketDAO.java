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

import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.exception.TicketException;
import com.hgd.ebp.state.TicketSearchState;
import com.hgd.ebp.util.WebUtil;

public class TicketDAO {
	private static final String SELECT_COUNT="SELECT COUNT(*) FROM ticket";
	private static final String SELECT_BY_PAGE="SELECT * FROM ticket";
	
	private DataSource ds;
	
	private static TicketDAO dao=new TicketDAO();
	public static TicketDAO getInstance()
	{
		return dao;
	}
	
	private TicketDAO()
	{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/ebpDS");
		} catch (NamingException e) {
			System.out.print("--------------没找到数据库连接池--------------\n");
		}
	}
	
	public int GetMaxCount(TicketSearchState state) throws TicketException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_COUNT,state);
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new TicketException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new TicketException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new TicketException(e);
		}
		
		try {
			results.next();
			return results.getInt(1);
		} catch (SQLException e) {
			System.out.print("--------------没有获取到结果--------------\n");
			throw new TicketException(e);
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
	
	public List<Ticket> getTicketByPage(TicketSearchState state) throws TicketException
	{
		List<Ticket> ticketList=new ArrayList<Ticket>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_BY_PAGE,state);
		sql+=" LIMIT ?, ?";
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new TicketException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
			stmt.setInt(1, state.getCurPage()*WebUtil.MAX_LINES);
			stmt.setInt(2, WebUtil.MAX_LINES);
			System.out.print("---------------------------------"+sql);
		} catch (SQLException e) {
			throw new TicketException(e);
		}
		
		try {
			System.out.print("--------------准备stmt失败--------------\n");
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new TicketException(e);
		}
		
		try {
			while(results.next())
			{
				ticketList.add(new Ticket(results.getInt(1),
						results.getString(2),results.getTimestamp(3),
						results.getInt(4),results.getInt(5),
						results.getDouble(6),results.getInt(7)));
			}
		} catch (SQLException e) {
			throw new TicketException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return ticketList;
	}
	
	public String SqlBuild(String sql,TicketSearchState state)
	{
		Timestamp starttime=state.getStarttime();
		Timestamp endtime=state.getEndtime();
		
		if((!"".equals(starttime)||!"".equals(endtime))&&(starttime!=null||endtime!=null))
		{
			sql+=" WHERE";
			boolean and=false;
			if(!"".equals(starttime)&&starttime!=null)
			{
				sql+=" starttime > "+"\""+starttime+"\"";
				and=true;
			}
			if(!"".equals(endtime)&&endtime!=null)
				sql+=(and ? " AND" : "")+" starttime < "+"\""+endtime+"\"";
		}
		return sql;
	}
}
