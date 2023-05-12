package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	// GET + POST 둘 다 응답
//	@RequestMapping("/login") // http://localhosts:8080/member/login

//	사용자 입력값 가져오기
//	 1) HttpServletRequest 사용하기
//	 2) 변수 사용 : 변수명은 폼 태그 name과 일치시켜 사용. 부득이 불일치하면 @RequestParam 사용
//	 3) 바인딩 객체 사용

//	@RequestMapping(value="/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public void loginGet(HttpServletRequest req) {
		log.info("login...");
		log.info("method " + req.getMethod());
	}

//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest req) {
//		log.info("login post...");
//		log.info("method "+req.getMethod());
//		System.out.println(req.getParameter("id"));
//		System.out.println(req.getParameter("password"));
//	}
	@PostMapping("/login")
	public void loginPost(@RequestParam("userid") String id, String password) {
		log.info("login post...");
		System.out.println("id " + id);
		System.out.println("password " + password);
	}

//	@RequestMapping("/register")
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
//		return "/member/register";
	}
}
