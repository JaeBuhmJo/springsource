package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.LoginDTO;
import com.spring.domain.RegisterDTO;

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
	public String loginPost(LoginDTO dto) {
		log.info("login post...");
		System.out.println("id : " + dto.getId());
		System.out.println("password : " + dto.getPassword());
		
		return "/member/main";
	}

//	@RequestMapping("/register")
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
//		return "/member/register";
	}
	
	// /member/register + POST처리
	// DTO 작성
	// 사용자 입력값이 잘 들어왔는지 확인
	// login.jsp 보여주기
	@PostMapping("/register")
	public String registerPost(RegisterDTO dto) {
		log.info("register post...");
		if(dto.getId().isEmpty()) {
			return "/member/register";
		}
		if(dto.getPassword().isEmpty()) {
			return "/member/register";
		}
		if(dto.getName().isEmpty()) {
			return "/member/register";
		}
		if(dto.getEmail().isEmpty()) {
			return "/member/register";
		}
		
		// redirect: 붙게 되면 DipatcherServlet이 동작
		// == response.sendRedirect()
		// => 포워드가 아니고 리다이렉트기때문에 겟방식으로 해당 주소로 이동하게 됨. 주소줄도.
//		return "redirect:/member/login"; 
		return "/member/login"; 
	}

}
