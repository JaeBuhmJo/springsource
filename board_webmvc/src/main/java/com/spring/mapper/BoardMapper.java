package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	public List<BoardDTO> list(Criteria cri);
	public int insert(BoardDTO boardDTO);
	public BoardDTO get(int bno);
	public int update(BoardDTO boardDTO);
	public int delete(int bno);
	public int totalCnt(Criteria cri);
}
