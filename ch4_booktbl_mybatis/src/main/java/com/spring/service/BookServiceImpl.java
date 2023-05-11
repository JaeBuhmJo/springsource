package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookDTO;
import com.spring.mapper.BookMapper;

@Service("bookService") // bookServiceImpl 이 기본 이름
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

//	public BookServiceImpl(BookDAO bookDAO) {
//		this.bookDAO = bookDAO;
//	}

	@Override
	public boolean insertBook(BookDTO insertDto) {
		return mapper.insert(insertDto) == 1 ? true : false;
	}

	@Override
	public boolean updateBook(int price, int code) {
		return mapper.update(price, code) == 1 ? true : false;
	}

	@Override
	public boolean deleteBook(int code) {
		return mapper.delete(code) == 1 ? true : false;
	}

	@Override
	public BookDTO getbook(int code) {
		return mapper.getRow(code);
	}

	@Override
	public List<BookDTO> getBookList() {
		return mapper.getRows();
	}

}
