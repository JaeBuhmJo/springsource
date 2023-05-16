package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;

	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 폼 요청");
	}

	@PostMapping("/login")
	public String loginPost(LoginDTO dto, HttpSession session) {
		log.info("로그인 시도");
		AuthDTO authDTO = service.login(dto);
		if(authDTO!=null) {
			session.setAttribute("authDTO", authDTO);
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}

	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃 요청");
		session.removeAttribute("authDTO");
		return "redirect:/";
	}

	@GetMapping("/step1")
	public void stepGet() {
		log.info("회원 가입 약관 보여주기");
	}

	@PostMapping("/step1")
	public String stepPost(boolean agree, RedirectAttributes rttr) {
		log.info("약관 동의 여부" + agree);
		// true -> register
		if (agree) {
			return "/member/register";
		} else {
			rttr.addFlashAttribute("check", false);
			return "redirect:/member/step1";
//			return "/member/step1";
		}
	}

	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		log.info("회원 가입" + dto);
		service.register(dto);
		return "redirect:/";
	}

}
