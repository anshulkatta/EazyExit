package com.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "UserManagement";
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView  getNodes() {
		return new ModelAndView("redirect:/node/discover");
	}
}