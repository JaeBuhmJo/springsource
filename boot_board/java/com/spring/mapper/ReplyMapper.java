package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {
	public ReplyDTO read(int rno);
	public int insert(ReplyDTO replyDTO);
	public List<ReplyDTO> listAll(@Param("cri") Criteria cri,@Param("bno") int bno);
	public int getCountByBno(int bno);
	public int update(ReplyDTO replyDTO);
	public int delete(int rno);
	public int deleteAll(int bno);
}
