package com.hgd.ebp.state;

import java.sql.Timestamp;

public class OrderSearchState extends PageSearchState {
	private Timestamp starttime;
	private Timestamp endtime;
	
	public OrderSearchState(){}

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
		return "OrderSearchState [starttime=" + starttime + ", endtime="
				+ endtime + "]";
	}
	
}
