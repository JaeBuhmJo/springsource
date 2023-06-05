package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.SpUserAuthorityDTO;
import com.spring.domain.SpUserDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public boolean register(SpUserDTO dto) {
		dto.setPassword(encoder.encode(dto.getPassword()));
		boolean result = mapper.register(dto) == 1 ? true : false;
		mapper.registerAuth(new SpUserAuthorityDTO(dto.getUserid(), "ROLE_ADMIN"));

		return result;
	}

}
