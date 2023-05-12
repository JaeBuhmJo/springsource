package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
//	@RequestMapping(value = "/read", method = RequestMethod.GET)
	@GetMapping("/read")
	public void readGet() {
		log.info("read...");
	}
//	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
	}
//	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@GetMapping("/modify")
	public void modifyGet() {
		log.info("modify...");
	}
//	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@GetMapping("/remove")
	public void removeGet() {
		log.info("remove...");
	}
}
