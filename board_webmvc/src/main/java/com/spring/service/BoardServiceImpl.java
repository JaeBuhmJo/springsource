package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;

@Service

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachMapper;

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Transactional
	@Override
	public boolean insert(BoardDTO boardDTO) {
		// board 테이블 + attach 테이블
		boolean insertFlag = mapper.insert(boardDTO) == 1 ? true : false;

		if (boardDTO.getAttachList() == null || boardDTO.getAttachList().size() == 0) {
			return insertFlag;
		}

		boardDTO.getAttachList().forEach(attach -> {
			attach.setBno(boardDTO.getBno());
			attachMapper.insert(attach);
		});
		return insertFlag;
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
