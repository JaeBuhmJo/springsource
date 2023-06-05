package com.spring.mapper;

import com.spring.domain.SpUserAuthorityDTO;
import com.spring.domain.SpUserDTO;

public interface MemberMapper {
	public int register(SpUserDTO spUserDTO);
	public int registerAuth(SpUserAuthorityDTO spUserAuthorityDTO);
	public SpUserDTO read(String userid);
}
