package com.hgd.ebp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hgd.ebp.domain.League;
import com.hgd.ebp.exception.LeagueException;
import com.hgd.ebp.state.LeagueQueryState;
import com.hgd.ebp.util.WebUtil;

@Repository
@Scope("singleton")
public class LeagueDAO {
	private static final String SELECT = "SELECT * FROM League";
	private static final String RETRIVE_COUNT = "SELECT count(lid) FROM League";
	private static final String RETRIVE_MAX_LID = "SELECT max(lid) FROM League";
	private static final String INSERT = "INSERT INTO League values(?, ?, ?, ?)";
	
	private DataSource ds;
	
	public LeagueDAO() {
		try {
			Context ctx = new InitialContext();   //1 获取JNDI
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/SoccerLeagueDS");   //2 从JNDI中找DS
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int queryMaxCount(LeagueQueryState state) throws LeagueException {
		Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        String sql = buildSQL(RETRIVE_COUNT, state);
        try {
        	connection = ds.getConnection();
            stmt = connection.prepareStatement(sql);
            results = stmt.executeQuery();
            
            results.next();
            int count = results.getInt(1);
            return count;
        } catch (Exception e) {
            throw new LeagueException(e);
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }
	}
	
	public List<League> queryByPage(LeagueQueryState state) throws LeagueException {
		Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        String sql = buildSQL(SELECT, state);
        sql += " LIMIT ?, ?";
        
        List<League> list = new ArrayList<League>();
        try {
        	connection = ds.getConnection();

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, state.getCurPage() * WebUtil.MAX_LINES);
            stmt.setInt(2, WebUtil.MAX_LINES);
            results = stmt.executeQuery();
            
            while (results.next()) {
                int lid = results.getInt(1);
                int year = results.getInt(2);
                String season = results.getString(3);
                String title = results.getString(4);
        
                League league = new League(lid, year, season, title);
                list.add(league);
            }
        } catch (Exception e) {
            throw new LeagueException(e);
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }
        
        return list;
	}
	
	private String buildSQL(String sql, LeagueQueryState state) {
        String qyear = state.getYear();
        String qseason = state.getSeason();
        if (!"".equals(qyear) || !"".equals(qseason)) {
        	String where = " WHERE";
        	boolean and = false;
        	
        	if (!"".equals(qyear)) {
        		where += " lyear=" + qyear;
        		and = true;
        	}
        	
        	if (!"".equals(qseason)) {
        		where += (and ? " AND" : "") + " season='" + qseason + "'"; 
        	}
        	
        	sql += where;
        }

        System.out.println("-----------" + sql);
        return sql;
	}
	
	public League insert(int year, String season, String title) throws LeagueException {
		Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        League league = null;
        try {
        	connection = ds.getConnection();

            stmt = connection.prepareStatement(RETRIVE_MAX_LID);
            results = stmt.executeQuery();
            results.next();
            int nextId = results.getInt(1) + 1;
            results.close();
            stmt.close();
            
            stmt = connection.prepareStatement(INSERT);
            stmt.setInt(1, nextId);
            stmt.setInt(2, year);
            stmt.setString(3, season);
            stmt.setString(4, title);
            stmt.executeUpdate();   
            
            league = new League(nextId, year, season, title);
        } catch (Exception e) {
        	throw new LeagueException(e);
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }
        return league;
	}
}
