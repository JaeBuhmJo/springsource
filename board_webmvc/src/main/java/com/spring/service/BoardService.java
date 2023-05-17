package com.spring.service;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardService {
	public List<BoardDTO> getList();
	public boolean register(BoardDTO boardDTO);
	public BoardDTO getRow(int bno);
}
