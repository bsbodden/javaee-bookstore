package com.integrallis.modernjee.bookstore.web.beans;

import java.util.List;

import javax.ejb.EJB;

import com.integrallis.modernjee.bookstore.domain.Book;
import com.integrallis.modernjee.bookstore.service.BookService;

public class Books {
    @EJB
    private BookService bookService;
    
    private String query;
	private List<Book> searchResults;
	
	private Book book;

	/*
	 * Actions
	 */
	
    public String search() {
        setSearchResults(bookService.getBooksWithTitleLike(query));
        return "success";
    }
    
	public String addBook() {
		bookService.addBook(book);
		return "afterAddBook";
	}
    
    /*
     * Getter and Setters 
     */
    
    public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setSearchResults(List<Book> searchResults) {
		this.searchResults = searchResults;
	}

	public List<Book> getSearchResults() {
		return searchResults;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
	
	public List<Book> getAll() {
		return bookService.findAll();
	}
}
