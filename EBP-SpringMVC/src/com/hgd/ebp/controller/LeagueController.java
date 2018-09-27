package com.hgd.ebp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.League;
import com.hgd.ebp.service.LeagueService;
import com.hgd.ebp.state.LeagueQueryState;

@Controller
public class LeagueController {
	@Resource
    private LeagueService leagueService;

	@RequestMapping(value="/ListLeaguesCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session, String page) {
		LeagueQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("LeagueQueryState");
			state = new LeagueQueryState();
		} else {
			state = (LeagueQueryState)
					session.getAttribute("LeagueQueryState");
			if (state == null) {
				state = new LeagueQueryState();
			}
		}
		
		List<League> list = null;
		try {
			int lastPage = leagueService.getLastPage(state);
			state.setLastPage(lastPage);
			
			list = leagueService.getLeaguesByPage(state, page);
			session.setAttribute("LeagueQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<League>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}

		model.addAttribute("listleagues", list); 
        return "ListLeagues";
	}

	@RequestMapping(value="/ListLeaguesCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session, String year, String season) {
		session.removeAttribute("LeagueQueryState");
		
		LeagueQueryState state = new LeagueQueryState(0, year, season);
		List<League> list = null;
		try {
			int lastPage = leagueService.getLastPage(state);
			state.setLastPage(lastPage);
			
			list = leagueService.getLeagues(state);
			session.setAttribute("LeagueQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<League>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}

		model.addAttribute("listleagues", list); 
        return "ListLeagues";
	}
	
	@RequestMapping(value="/admin/AddLeagueCtrl", method=RequestMethod.POST)
    public String add(Model model, String year, String season, String title) {
		Map<String, String> errMap = new HashMap<String, String>();

		int lyear = -1;
		try {
			lyear = Integer.parseInt(year);
			if (lyear < 2018 || lyear > 2023) {
				errMap.put("year", "年份范围应在2018-2030之间");
			}
		} catch (Exception e) {
			errMap.put("year", "请输入正确年份");				
		}

		if (season == null || "".equals(season)) {
			errMap.put("season", "请选择联赛季节");
		}
		
		if (title == null || "".equals(title)) {
			errMap.put("title", "请输入正确名称");
		}
		
		if (errMap.size() != 0) {
			model.addAttribute("errMap", errMap);
	        return "admin/AddLeague";
		}
		
		try {
			League league = leagueService.addLeague(lyear, season, title);
			model.addAttribute("league", league);
		} catch (Exception e) {
			e.printStackTrace();
			
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
			
			return "admin/AddLeague";
		}
		
		return "admin/AddLeagueSucc";
	}
}
