package com.hgd.ebp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminLoginException;
import com.hgd.ebp.filter.LoginFilter;
import com.hgd.ebp.service.AdminService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Resource
	private AdminService adminSvc;
	
	@RequestMapping(value="/LoginCtrl", method=RequestMethod.POST)
	private String login(Model model, HttpSession session,
			String username, String password) {
		Map<String, String> errMap = new HashMap<String, String>();
		if (username == null || username.equals("")) {
			errMap.put("username", "用户名不能为空");
		}
		if (password == null || password.equals("")) {
			errMap.put("password", "密码不能为空");
		}
		
		if (errMap.size() > 0) {
			model.addAttribute("errMap", errMap);
			return "admin/Login";
		}
		
		Admin admin = null;
		try {
			admin = adminSvc.adminLogin(username, password);
		} catch (Exception e) {
			errMap.put("GLOBAL", (e instanceof AdminLoginException) ? 
					e.getMessage() : "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
			
        	return "admin/Login";
		} 
		
		session.setAttribute(LoginFilter.ATTR_ADMINUSER, admin);
        return "redirect:../";
	}
	
	@RequestMapping(value="/LogoutCtrl", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(LoginFilter.ATTR_ADMINUSER);
		return "redirect:../";
	}
}
