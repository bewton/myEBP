package com.hgd.ebp.domain;

import java.sql.Timestamp;

public class User {
	
	private int uid;
	private String username;
	private String password;
	private String name;
	private String gender;
	private String idcard;
	private String address;
	private String telno;
	private Timestamp regtime;
	private double balance;
	private int status;
	
	public User()
	{
		
	}
	
	public User(int uid,String username,String password,String name,String gender,String idcard
			,String address,String telno,Timestamp regtime,double balance,int status)
	{
		this.uid=uid;
		this.username=username;
		this.password=password;
		this.name=name;
		this.gender=gender;
		this.idcard=idcard;
		this.address=address;
		this.telno=telno;
		this.regtime=regtime;
		this.balance=balance;
		this.status=status;
		
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
