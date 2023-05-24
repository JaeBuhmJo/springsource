package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;

public interface ReplyService {
	public ReplyDTO read(int rno);
	public boolean insert(ReplyDTO replyDTO);
	public List<ReplyDTO> listAll(Criteria cri, int bno);
	public boolean update(ReplyDTO replyDTO);
	public boolean delete(int rno);
}
