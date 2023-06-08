package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper reMapper;
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public ReplyDTO read(int rno) {
		return reMapper.read(rno);
	}

	@Transactional
	@Override
	public boolean insert(ReplyDTO replyDTO) {
		mapper.updateReplyCnt(replyDTO.getBno(), 1);
		//각각 다른 테이블에 작업중 => 트랜잭션 관리 필요
		return reMapper.insert(replyDTO) == 1 ? true : false;
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {
		List<ReplyDTO> list = reMapper.listAll(cri, bno);
		int replyCnt = reMapper.getCountByBno(bno);
		return new ReplyPageDTO(replyCnt, list);
	}

	@Override
	public boolean update(ReplyDTO replyDTO) {
		return reMapper.update(replyDTO) == 1 ? true : false;
	}

	@Transactional
	@Override
	public boolean delete(int rno) {
		ReplyDTO replyDTO = reMapper.read(rno);
		mapper.updateReplyCnt(replyDTO.getBno(), -1);
		return reMapper.delete(rno) == 1 ? true : false;
	}
}
