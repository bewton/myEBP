package com.hgd.ebp.state;

public class PageSearchState {
	private int lastPage=0;
	private int curPage=0;
	
	public PageSearchState()
	{
		
	}
	
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
}
