package com.hgd.ebp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.LeagueDAO;
import com.hgd.ebp.domain.League;
import com.hgd.ebp.exception.LeagueException;
import com.hgd.ebp.state.LeagueQueryState;
import com.hgd.ebp.util.WebUtil;

@Service
@Scope("singleton")
public class LeagueService {
	@Resource
	private LeagueDAO leagueDAO;
	
	public int getLastPage(LeagueQueryState state) throws LeagueException {
		int count = leagueDAO.queryMaxCount(state);
		
		int maxPage = (count + WebUtil.MAX_LINES - 1) / WebUtil.MAX_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	
	public List<League> getLeaguesByPage(LeagueQueryState state, 
			String page) throws LeagueException {
		int curPage = state.getCurPage();
		switch (page) {
			case "0":
				curPage = 0;
				break;
			case "prev":
				if (curPage > 0) curPage--;
				break;
			case "next":
				if (curPage < state.getLastPage()) curPage++;
				break;
			default:
				curPage = state.getLastPage();
				break;
		}
		state.setCurPage(curPage);
		
		List<League> list = leagueDAO.queryByPage(state);
        return list;
	}
	
	public List<League> getLeagues(LeagueQueryState state) throws LeagueException {
		List<League> list = leagueDAO.queryByPage(state);
        return list;
	}
	
	public League addLeague(int year, String season, String title) throws LeagueException {
		League league = leagueDAO.insert(year, season, title);
        return league;
	}
}










