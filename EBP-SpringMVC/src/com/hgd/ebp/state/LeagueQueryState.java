package com.hgd.ebp.state;

public class LeagueQueryState extends PageQueryState {
	private String year = "";
    private String season = "";
    
    public LeagueQueryState() {}
    
	public LeagueQueryState(int curPage, String year, String season) {
		setCurPage(curPage);
		this.year = year;
		this.season = season;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "LeagueQueryState [year=" + year + ", season=" + season
				+ ", getLastPage()=" + getLastPage() + ", getCurPage()="
				+ getCurPage() + "]";
	}
}
