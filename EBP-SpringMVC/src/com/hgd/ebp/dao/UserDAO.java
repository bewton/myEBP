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

import com.hgd.ebp.domain.User;
import com.hgd.ebp.exception.UserException;
import com.hgd.ebp.state.UserSearchState;
import com.hgd.ebp.util.WebUtil;


public class UserDAO {
	private static final String SELECT_ALL_USER="SELECT * FROM user";
	private static final String ADD_AN_USER="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_MAX_UID="SELECT MAX(uid) FROM user";
	private static final String SELECT_USER_BY_USRENAME="SELECT * FROM user WHERE username=?";
	private static final String SELECT_USER_BY_TIME="SELECT * FROM user WHERE regtime BETWEEN ? AND ?";
	
	private static final String SELECT_COUNT="SELECT COUNT(*) FROM user";
	private static final String SELECT_BY_PAGE="SELECT * FROM user";
	
	private DataSource ds;
	
	private static UserDAO dao=new UserDAO();
	public static UserDAO getInstance()
	{
		return dao;
	}
	
	private UserDAO()
	{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/ebpDS");
		} catch (NamingException e) {
			System.out.print("--------------没找到数据库连接池--------------\n");
		}
	}
	
	public List<User> GetAllUser() throws UserException
	{
		List<User> userList = new ArrayList<User>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_ALL_USER);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			while(results.next())
			{
				userList.add(new User(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getString(5),results.getString(6),results.getString(7),
						results.getString(8),results.getTimestamp(9),results.getDouble(10),results.getInt(11)));
			}
		} catch (SQLException e) {
			throw new UserException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return userList;
	}
	
	public void AddUser(User user) throws UserException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		int nMaxUid=0;
		int iResult=0;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_MAX_UID);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败1--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败1--------------\n");
			throw new UserException(e);
		}
		
		try {
			while(results.next())
			{
				nMaxUid=results.getInt(1)+1;
			}
			user.setUid(nMaxUid);
		} catch (SQLException e1) {
			throw new UserException(e1);
		}
		
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e1) {}
		if(results!=null)
			try {results.close();} catch (SQLException e1) {}
		
		try {
			stmt=connection.prepareStatement(ADD_AN_USER);
			stmt.setInt(1, user.getUid());
			stmt.setString(2,user.getUsername());
			stmt.setString(3,user.getPassword());
			stmt.setString(4,user.getName());
			stmt.setString(5,user.getGender());
			stmt.setString(6,user.getIdcard());
			stmt.setString(7,user.getAddress());
			stmt.setString(8,user.getTelno());
			stmt.setTimestamp(9,user.getRegtime());
			stmt.setDouble(10,user.getBalance());
			stmt.setInt(11,user.getStatus());
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败2--------------\n");
			throw new UserException(e);
		}
		
		try {
			iResult=stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败2--------------\n");
			throw new UserException(e);
		}
		
		System.out.print(iResult);
		System.out.print("行受影响\n");
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
	}
	
	public User SearchUserByUsername(User user) throws UserException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		User v_user=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_USER_BY_USRENAME);
			stmt.setString(1, user.getUsername());
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			while(results.next())
			{
				if(results.getString(3).equals(user.getPassword()))
				{
					v_user=new User(results.getInt(1),results.getString(2),results.getString(3),
							results.getString(4),results.getString(5),results.getString(6),results.getString(7),
							results.getString(8),results.getTimestamp(9),results.getDouble(10),results.getInt(11));
				}
			}
		} catch (SQLException e) {
			throw new UserException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return v_user;
	}
	
	public List<User> SearchUserByTime(Timestamp bTime,Timestamp eTime) throws UserException
	{
		List<User> userList=new ArrayList<User>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_USER_BY_TIME);
			stmt.setTimestamp(1,bTime);
			stmt.setTimestamp(2,eTime);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			while(results.next())
			{
				userList.add(new User(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getString(5),results.getString(6),results.getString(7),
						results.getString(8),results.getTimestamp(9),results.getDouble(10),results.getInt(11)));
			}
		} catch (SQLException e) {
			throw new UserException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return userList;
	}
	
	
	
	public int GetMaxCount(UserSearchState state) throws UserException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_COUNT,state);
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results.next();
			return results.getInt(1);
		} catch (SQLException e) {
			System.out.print("--------------没有获取到结果--------------\n");
			throw new UserException(e);
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
	
	public List<User> getUserByPage(UserSearchState state) throws UserException
	{
		List<User> userList=new ArrayList<User>();
		
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		
		String sql=SqlBuild(SELECT_BY_PAGE,state);
		sql+=" LIMIT ?, ?";
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			stmt=connection.prepareStatement(sql);
			stmt.setInt(1, state.getCurPage()*WebUtil.MAX_LINES);
			stmt.setInt(2, WebUtil.MAX_LINES);
			System.out.print("---------------------------------"+sql+"\n");
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new UserException(e);
		}
		
		try {
			while(results.next())
			{
				userList.add(new User(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getString(5),results.getString(6),results.getString(7),
						results.getString(8),results.getTimestamp(9),results.getDouble(10),results.getInt(11)));
			}
		} catch (SQLException e) {
			throw new UserException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return userList;
	}
	
	public String SqlBuild(String sql,UserSearchState state)
	{
		Timestamp starttime=state.getStarttime();
		Timestamp endtime=state.getEndtime();
		
		if((!"".equals(starttime)||!"".equals(endtime))&&(starttime!=null||endtime!=null))
		{
			sql+=" WHERE";
			boolean and=false;
			if(!"".equals(starttime)&&starttime!=null)
			{
				sql+=" regtime > "+"\""+starttime+"\"";
				and=true;
			}
			if(!"".equals(endtime)&&endtime!=null)
				sql+=(and ? " AND" : "")+" regtime < "+"\""+endtime+"\"";
		}
		return sql;
	}
}
