package com.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {

	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 폼 요청 ");
	}

	@PreAuthorize("hasAnyAuthority('ROLE_USER')") // security-context.xml의 intercept-url과 같은 역할. 여러개 들어와도 됨
	@GetMapping("/userpage")
	public void userPage() {
		log.info("유저 페이지 요청 ");
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@GetMapping("/adminpage")
	public void adminPage() {
		log.info("어드민 페이지 요청 ");
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", "아이디나 비밀번호를 확인해 주세요");
		return "/security/login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/security/denied";
	}

	@GetMapping("/auth")
	@ResponseBody
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
