package com.hgd.ebp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
	@RequestMapping(value="/AddShoppingCartCtrl")
	public String add(Model model, HttpSession session, int num) {
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>)session.getAttribute("numList");
		if (list == null) {
			list = new ArrayList<>();
		}
		
		list.add(num);
		session.setAttribute("numList", list);
		
		model.addAttribute("succ", "向购物车添加" + num + "件球衣");
		return "ShoppingCart";
	}
}
