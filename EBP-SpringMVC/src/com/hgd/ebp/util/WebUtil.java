package com.hgd.ebp.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hgd.ebp.service.AdminService;
import com.hgd.ebp.service.LeagueService;

public class WebUtil {
	public static final int MAX_LINES = 3;
	
    public static LeagueService getLeagueServiceBean(ServletContext sc) {
    	ApplicationContext context = 
    			WebApplicationContextUtils.getWebApplicationContext(sc);
        return (LeagueService)context.getBean("leagueService");
    }
    
    public static AdminService getAdminServiceBean(ServletContext sc) {
    	ApplicationContext context = 
    			WebApplicationContextUtils.getWebApplicationContext(sc);
        return (AdminService)context.getBean("adminService");
    }
}
