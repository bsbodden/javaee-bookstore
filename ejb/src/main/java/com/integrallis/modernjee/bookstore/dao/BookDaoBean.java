package com.integrallis.modernjee.bookstore.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Query;

import com.integrallis.modernjee.bookstore.domain.Book;

@Stateful
public class BookDaoBean extends BaseDataAccessObject<Long, Book> implements BookDao {

	public Book findByISBN(String isbn) {
		Query query = entityManager.createNamedQuery(Book.QUERY_FIND_BY_ISBN);
		query.setParameter("isbn", isbn);
		return (Book) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		Query query = entityManager.createQuery("SELECT b FROM Book b");
		return (List<Book>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAllCostingMoreThan(double d) {
		Query query = entityManager.createNamedQuery(Book.QUERY_FIND_ALL_COSTING_MORE_THAN);
		query.setParameter("price", d);
		return (List<Book>)query.getResultList();
	}

}
