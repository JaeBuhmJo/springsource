package com.spring.mapper;

import java.util.List;

import com.spring.domain.BookDTO;

public interface BookMapper {
	public int insert(BookDTO dto);
	public int delete(int code);
	public int update(BookDTO dto);
	public BookDTO read(int code);
	public List<BookDTO> getRows();
	public List<BookDTO> getSearchList(String criteria, String keyword);
	
}
