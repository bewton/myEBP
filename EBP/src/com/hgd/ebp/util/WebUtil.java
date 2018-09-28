package com.hgd.ebp.util;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hgd.ebp.service.AdminService;

public class WebUtil {
	public static int MAX_LINES=8;
	
	public static String GetMapData(Map<String,String> map,String key)
	{
		if(null==map)
			return "";
		String value=map.get(key);
		if(null==value)
			return "";
		return value;
	}
	
	public static AdminService getAdminServiceBean(ServletContext sc)
	{
		ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(sc);
		return (AdminService)context.getBean("adminService");
	}
}
