package com.spring.service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;

public interface ReplyService {
	public ReplyDTO read(int rno);
	public boolean insert(ReplyDTO replyDTO);
//	public List<ReplyDTO> getList(Criteria cri, int bno);
	public ReplyPageDTO getList(Criteria cri, int bno);
	public boolean update(ReplyDTO replyDTO);
	public boolean delete(int rno);
	
}
