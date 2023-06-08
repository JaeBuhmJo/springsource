package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	@Autowired
	private BCryptPasswordEncoder bPasswordEncoder;

	@Override
	public AuthDTO login(LoginDTO loginDTO) {
		String encodePassword = mapper.getPassword(loginDTO.getUserid());
		if (bPasswordEncoder.matches(loginDTO.getPassword(), encodePassword)) {
			loginDTO.setPassword(encodePassword);
			return mapper.login(loginDTO.getUserid());
		}
		return null;
	}

	@Override
	public boolean register(MemberDTO memberDTO) {
		// 비밀번호 암호화 : encode(암호화할 원본 코드)
		// matched(암호화하기 전, 암호화 코드)
		// 1234, 암호화된 코드
		memberDTO.setPassword(bPasswordEncoder.encode(memberDTO.getPassword()));
		return mapper.insert(memberDTO) == 1 ? true : false;
	}

	@Override
	public boolean dupId(String userid) {
		return mapper.dupId(userid) == 0 ? true : false;
	}

	@Override
	public boolean remove(LoginDTO loginDTO) {
		String encodePassword = mapper.getPassword(loginDTO.getUserid());
		if (bPasswordEncoder.matches(loginDTO.getPassword(), encodePassword)) {
			return mapper.leave(loginDTO.getUserid()) == 1 ? true : false;
		} else {
			return false;
		}
	}

	@Override
	public boolean changePwd(ChangeDTO changeDTO) {
		String encodePassword = mapper.getPassword(changeDTO.getUserid());
		if (bPasswordEncoder.matches(changeDTO.getCurrentPassword(), encodePassword)) {
			changeDTO.setNewPassword(bPasswordEncoder.encode(changeDTO.getNewPassword()));
			return mapper.changePwd(changeDTO) == 1 ? true : false;
		}
		return false;
	}

}
