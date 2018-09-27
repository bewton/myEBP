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

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminException;

@Repository
@Scope("singleton")
public class AdminDAO {
    private static final String RETRIEVE_STMT = 
            "SELECT * FROM Adminuser WHERE username=? AND password=?";
    private DataSource ds;
    
    public AdminDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/SoccerLeagueDS");
        } catch (NamingException e) {}
    }
    
    public List<Admin> queryByNamePassword(String name, String password) 
            throws AdminException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        List<Admin> list = new ArrayList<Admin>();
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);

            stmt.setString(1, name);
            stmt.setString(2, password);
            results = stmt.executeQuery();

            while (results.next()) { 
            	Admin admin = new Admin(results.getInt("uid"), 
            			results.getString("username"), results.getString("password"));
            	list.add(admin);
            }
            return list;
        } catch (SQLException e) {
            throw new AdminException(e.getMessage());
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException se) { }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException se) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (Exception e) { }
            }
        } 
    } 
}
