package com.hgd.ebp.state;

import java.sql.Timestamp;

public class UserSearchState extends PageSearchState {
	private Timestamp starttime;
	private Timestamp endtime;
	
	public UserSearchState(){}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String toString() {
		return "UserSearchState [starttime=" + starttime + ", endtime="
				+ endtime + "]";
	}
	
}
