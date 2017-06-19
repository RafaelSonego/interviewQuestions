package com.sonego.interviewQuestions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {

	@RequestMapping("/404")
	public String notFound() {
		return "/errorPage/404";
	}
	
	@RequestMapping("/403")
	public String forbidden() {
		return "/errorPage/403";
	}
	
	@RequestMapping("/500")
	public String internalServerError() {
		return "/errorPage/500";
	}
	
}
