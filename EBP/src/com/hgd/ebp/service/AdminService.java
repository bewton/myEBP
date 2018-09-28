package com.hgd.ebp.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.AdminDAO;
import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminException;

@Service("adminService")
@Scope("singleton")
public class AdminService {
	@Resource
	private AdminDAO dao;
	
	private static AdminService adminService=new AdminService();
	
	public static AdminService getInstance()
	{
		return adminService;
	}
	
	public Admin VertifyUser(Admin admin) throws AdminException
	{
		dao=AdminDAO.getInstance();
		return dao.SearchAdminByUsername(admin);
	}
}
