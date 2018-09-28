package com.hgd.ebp.domain;

import java.sql.Timestamp;

public class Ticket {
	private int tid;
	private String des;
	private Timestamp starttime;
	private int amount;
	private int balance;
	private double price;
	private int status;
	
	public Ticket()
	{
		
	}
	
	public Ticket(int tid,String des,Timestamp starttime,int amount,int balance,double price,int status)
	{
		this.tid=tid;
		this.des=des;
		this.starttime=starttime;
		this.amount=amount;
		this.balance=balance;
		this.price=price;
		this.status=status;
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
