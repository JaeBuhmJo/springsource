package com.spring.mapper;

import java.util.List;

import com.spring.domain.AttachFileDTO;

public interface AttachMapper {
	public int insert(AttachFileDTO attachFileDTO);
	public List<AttachFileDTO> select(int bno);
	public int deleteAll(int bno);
	public List<AttachFileDTO> oldFiles();
}
