package com.sonego.interviewQuestions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class InterviewQuestionController {
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView execute() {
		return new ModelAndView("public/login");
	}
	
	@RequestMapping(method = {RequestMethod.POST}, value = "validateLogin")
	public ModelAndView validateLogin() {
		return new ModelAndView("protected/index");
	}
}
