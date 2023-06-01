package com.spring.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // http://localhost:8080 요청 응답 컨트롤러
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * value : 해당 컨트롤러가 어떤 요청을 처리할 것인지 작성
	 * 
	 * http://localhost:8080/ + GET
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "index";
	}

//	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	@GetMapping("/doA")
	public String doA() {
		logger.info("doA...");
		return "result";
	}

}
