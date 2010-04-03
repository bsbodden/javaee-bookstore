package com.integrallis.modernjee.bookstore.dao;

import java.util.List;

import com.integrallis.modernjee.bookstore.domain.Book;

public interface BookDao extends DataAccessObject<Long, Book> {
    Book findByISBN(String isbn);
    List<Book> findAllBooks();
	List<Book> findAllCostingMoreThan(double d);
}
