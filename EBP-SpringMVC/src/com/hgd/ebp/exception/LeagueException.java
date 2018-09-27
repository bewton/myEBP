package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class LeagueException extends Exception {
	public LeagueException(Exception e) {
		super(e);
	}
	
	public LeagueException(String msg) {
		super(msg);
	}
}
