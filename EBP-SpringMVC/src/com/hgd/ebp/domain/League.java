package com.hgd.ebp.domain;

public class League {   //项目中的实体类Entity=domain
	private int lid;
	private int year;
	private String season;
	private String title;
	
	public League(int lid, int year, String season, String title) {
		this.lid = lid; 
		this.year = year; 
		this.season = season; 
		this.title = title; 
	}
	
	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
