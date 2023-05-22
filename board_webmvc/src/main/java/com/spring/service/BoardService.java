package com.spring.service;

import java.util.List;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;

public interface BoardService {
	public List<BoardDTO> getList(Criteria cri);
	public boolean insert(BoardDTO boardDTO);
	public BoardDTO getRow(int bno);
	public boolean update(BoardDTO boardDTO);
	public boolean delete(int bno);
	public int getTotalCnt(Criteria cri);
}
