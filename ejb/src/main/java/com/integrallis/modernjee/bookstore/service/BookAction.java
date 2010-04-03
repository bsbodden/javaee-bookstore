package com.integrallis.modernjee.bookstore.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.integrallis.modernjee.bookstore.dao.BookDao;
import com.integrallis.modernjee.bookstore.domain.Book;

@Stateless
public class BookAction implements BookService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	BookDao bookDao;

	@SuppressWarnings("unchecked")
	public List<Book> getBooksWithTitleLike(String keyword) {
		Query query = entityManager.createQuery("SELECT b FROM Book b WHERE UPPER(b.title) LIKE :keyword ORDER BY b.title");
		query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		return (List<Book>) query.getResultList();
	}

	public void addBook(Book book) {
        bookDao.persist(book);
	}

	public List<Book> findAll() {
		return bookDao.findAllBooks();
	}

}
