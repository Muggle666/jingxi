package com.jingxi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jingxi.portal.service.LogoutService;

@Controller
public class LogoutController {

	@Autowired
	private LogoutService logoutService;
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String Logout(){
		System.out.println("LogoutController--Logout()");
		logoutService.logout();
		return "index";
	}
}
