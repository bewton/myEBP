package com.hgd.ebp.domain;

import java.sql.Timestamp;

public class Order {
	private int oid;
	private String des;
	private Timestamp committime;
	private double account;
	private String name;
	private String username;
	private String idcard;
	
	public Order()
	{
		
	}
	
	public Order(int oid,String des,Timestamp committime,double account,String name,String username,String idcard)
	{
		this.oid=oid;
		this.des=des;
		this.committime=committime;
		this.account=account;
		this.name=name;
		this.username=username;
		this.idcard=idcard;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Timestamp getCommittime() {
		return committime;
	}
	public void setCommittime(Timestamp committime) {
		this.committime = committime;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
}
