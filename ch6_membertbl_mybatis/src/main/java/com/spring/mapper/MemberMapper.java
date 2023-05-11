package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public int insertMember(MemberDTO dto);
	public int updateMember(MemberDTO dto);
	public int deleteMember(@Param("userid") String userid, @Param("password") String password);
	public MemberDTO getRow(@Param("userid") String userid, @Param("password") String password);
	public List<MemberDTO> getRows();
}
