package com.spring.service;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberService {
	public AuthDTO login(LoginDTO loginDTO);
	public boolean register(MemberDTO memberDTO);
}
