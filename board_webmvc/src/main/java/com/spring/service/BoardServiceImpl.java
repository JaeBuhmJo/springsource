package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private ReplyMapper replyMapper;
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
//		BoardDTO dto =  mapper.readAttach(bno);
//		log.info("상세 + 파일 첨부 "+dto);
		return mapper.get(bno);
	}

	@Transactional
	@Override
	public boolean update(BoardDTO boardDTO) {
		boolean updateFlag = mapper.update(boardDTO) == 1 ? true : false;

		// 기존 첨부목록 제거
		attachMapper.deleteAll(boardDTO.getBno());

		// 첨부파일이 있다면
		if (boardDTO.getAttachList() == null || boardDTO.getAttachList().size() == 0) {
			return updateFlag;
		}

		// 첨부목록 삽입
		boardDTO.getAttachList().forEach(attach -> {
			attach.setBno(boardDTO.getBno());
			attachMapper.insert(attach);
		});

		return updateFlag;
	}

	@Transactional
	@Override
	public boolean delete(int bno) {
		// 자식 댓글 삭제
		replyMapper.deleteAll(bno);
		// 첨부파일 삭제
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1 ? true : false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> getAttachList(int bno) {
		return attachMapper.select(bno);
	}

}
