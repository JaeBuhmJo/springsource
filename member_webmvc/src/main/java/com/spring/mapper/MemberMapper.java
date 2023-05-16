package com.spring.mapper;

import com.spring.domain.AuthDTO;
import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public AuthDTO login(String userid);
	public int insert(MemberDTO memberDTO);
	public String getPassword(String userid);
}