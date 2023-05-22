package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardMapper;

@Service

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public boolean insert(BoardDTO boardDTO) {
		return mapper.insert(boardDTO) == 1 ? true : false;
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.get(bno);
	}

	@Override
	public boolean update(BoardDTO boardDTO) {
		return mapper.update(boardDTO) == 1 ? true : false;
	}

	@Override
	public boolean delete(int bno) {
		return mapper.delete(bno) == 1 ? true : false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

}
