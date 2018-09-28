package com.hgd.ebp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminException;

@Repository
@Scope("singleton")
public class AdminDAO {
	private static final String SELECT_ADMIN_BY_USRENAME="SELECT * FROM adminuser WHERE username=?";
	
	private DataSource ds;
	
	private static AdminDAO adminDAO=new AdminDAO();
	
	public static AdminDAO getInstance()
	{
		return adminDAO;
	}
	
	private AdminDAO()
	{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/ebpDS");
		} catch (NamingException e) {
			System.out.print("--------------没找到数据库连接池--------------\n");
		}
	}
	
	public Admin SearchAdminByUsername(Admin admin) throws AdminException
	{
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet results=null;
		Admin v_admin=null;
		
		try {
			connection=ds.getConnection();
		} catch (SQLException e) {
			System.out.print("--------------数据库链接失败--------------\n");
			throw new AdminException(e);
		}
		
		try {
			stmt=connection.prepareStatement(SELECT_ADMIN_BY_USRENAME);
			stmt.setString(1, admin.getUsername());
		} catch (SQLException e) {
			System.out.print("--------------准备stmt失败--------------\n");
			throw new AdminException(e);
		}
		
		try {
			results=stmt.executeQuery();
		} catch (SQLException e) {
			System.out.print("--------------语句执行失败--------------\n");
			throw new AdminException(e);
		}
		
		try {
			while(results.next())
			{
				if(results.getString(2).equals(admin.getPassword()))
				{
					v_admin=new Admin(results.getString(1),results.getString(2));
				}
			}
		} catch (SQLException e) {
			throw new AdminException(e);
		}
		
		if(connection!=null)
			try {connection.close();} catch (SQLException e) {}
		if(stmt!=null)
			try {stmt.close();} catch (SQLException e) {}
		if(results!=null)
			try {results.close();} catch (SQLException e) {}
		
		return v_admin;
	}
}
