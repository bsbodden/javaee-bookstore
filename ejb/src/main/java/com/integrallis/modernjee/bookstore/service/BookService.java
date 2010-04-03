package com.integrallis.modernjee.bookstore.service;

import java.util.List;

import javax.ejb.Local;

import com.integrallis.modernjee.bookstore.domain.Book;

@Local
public interface BookService {
	List<Book> getBooksWithTitleLike(String keyword);

	void addBook(Book book);

	List<Book> findAll();
}
