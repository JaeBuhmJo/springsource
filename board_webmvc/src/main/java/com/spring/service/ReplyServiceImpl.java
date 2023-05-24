package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper reMapper;

	@Override
	public ReplyDTO read(int rno) {
		return reMapper.read(rno);
	}

	@Override
	public boolean insert(ReplyDTO replyDTO) {
		return reMapper.insert(replyDTO) == 1 ? true : false;
	}

	@Override
	public List<ReplyDTO> listAll(Criteria cri, int bno) {
		return reMapper.listAll(cri, bno);
	}

	@Override
	public boolean update(ReplyDTO replyDTO) {
		return reMapper.update(replyDTO) == 1 ? true : false;
	}

	@Override
	public boolean delete(int rno) {
		return reMapper.delete(rno) == 1 ? true : false;
	}
}
