package com.hgd.ebp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="PerformanceFilter", urlPatterns={"/*"})
public class PerformanceFilter implements Filter {
	private FilterConfig fConfig;

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		long begTime = System.currentTimeMillis();   //1970.1.1 0:0
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis(); 
		
		StringBuffer msg = ((HttpServletRequest)request).getRequestURL();
		msg.insert(0, "性能测试： [");
		msg.append("] 用时 ");
		msg.append(endTime - begTime);
		msg.append("ms.");

		ServletContext application = fConfig.getServletContext();  //获取全局作用域
		application.log(msg.toString());
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
