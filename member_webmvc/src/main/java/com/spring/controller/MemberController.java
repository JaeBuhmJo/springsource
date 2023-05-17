package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
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
		if (authDTO != null) {
			session.setAttribute("authDTO", authDTO);
			return "redirect:/";
		} else {
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

	@PostMapping("/dupId")
	@ResponseBody // 컨트롤러 작업이 완료될 때 결과값으로 리턴 (ViewResolver 작동 방지)
	public String dupIdCheck(String userid) {
		log.info("아이디 중복 확인" + userid);
		boolean idCheck = service.dupId(userid);
		if (idCheck) {
			return "true";
		} else {
			return "false";
		}
	}

	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원 탈퇴 페이지 이동");
	}

	@PostMapping("/leave")
	public String leavePost(LoginDTO loginDTO, HttpSession session) {
		log.info("회원 탈퇴 요청");
		if (service.remove(loginDTO)) {
			session.invalidate();
			return "redirect:/";
		}
		return "redirect:/member/leave";
	}

	@GetMapping("/changePwd")
	public void changePwdGet() {
		log.info("비밀번호 변경 페이지 이동");
	}

	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDTO, HttpSession session) {
		log.info("비밀번호 변경 요청");
		if (changeDTO.passwordEquals()) {

			if (service.changePwd(changeDTO)) {
				session.invalidate();
				return "redirect:/member/login";
			}
		}
		return "redirect:/member/changePwd";
	}
}
