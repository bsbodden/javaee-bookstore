package com.integrallis.modernjee.bookstore.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.integrallis.modernjee.bookstore.dao.BookDao;
import com.integrallis.modernjee.bookstore.dao.StoreDao;

public class BookStoreORMTests {
	
	@Test
	public void testGetStore() throws Exception {
		Store store = (Store) storeDao.findById(1L);
		logger.info(store.getNickName());
		assertEquals("B&N Desert Ridge", store.getNickName());
		assertEquals("21001 N. Tatum Blvd. Suite 42", store.getAddress()
				.getStreet1());
		assertEquals("Phoenix", store.getAddress().getCity());
		assertEquals("AZ", store.getAddress().getState());
	}
	
	@Test
	public void testGetAllStores() {
		List<Store> allStores = storeDao.getAllStores();
		for (Store store : allStores) {
			logger.info("Store: " + store.getNickName());
		}
		assertEquals(2, allStores.size());
	}
	
	@Test
	public void testGenericDaoGetAllEntities() {
		List<Store> allStores = storeDao.findAll();
		for (Store store : allStores) {
			logger.info("Store: " + store.getNickName());
		}
		assertEquals(2, allStores.size());
	}
	

	@Test
	public void testGetAllBooksOver30Dollars() {
		List<Book> booksOver30 = bookDao.findAllCostingMoreThan(30.0); 
		for (Book book : booksOver30) {
			logger.info("Book: " + book.getTitle() + " cost $"
					+ book.getPrice());
			assertTrue(book.getPrice() > 30);
		}
	}
	
	@Test
	public void testGetBookByISBNQuery() {
		Book book = (Book) bookDao.findByISBN("1590595823");
		assertEquals("1590595823", book.getIsbn());
	}
	
	/**
	 * Lab 2.4
	 */
	@Test
	public void testAuthorsValueMapping() {
		logger.info("###### Lab 2.4 - Value Mappings #####");
		List<String> expectedAuthors = new ArrayList<String>();
		expectedAuthors.add("Schutta");
		expectedAuthors.add("Asleson");

		Book book = (Book) bookDao.findByISBN("1590595823");
		
		assertEquals(expectedAuthors.size(), book.getAuthors().size());

		assertEquals("1590595823", book.getIsbn());
		for (String author : book.getAuthors()) {
			logger.info("Author:" + author + " wrote " + book.getTitle());
			assertTrue(expectedAuthors.contains(author));
		}
		logger.info("###### End Lab 2.4 - Value Mappings #####");
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInheritanceMapping() {
		Map<String,Class> isbnToClass = new HashMap<String,Class>();
		isbnToClass.put("1590595963", ElectronicBook.class);
		isbnToClass.put("1590597923", ElectronicBook.class);
		isbnToClass.put("0596519788", Book.class);
		isbnToClass.put("1934356093", Book.class);
		isbnToClass.put("0978739299", Book.class);
		isbnToClass.put("1590595823", Book.class);
		isbnToClass.put("0321130006", Book.class);
		isbnToClass.put("1932394842", Book.class);
		
        List<Book> allBooks = bookDao.findAllBooks();
        for (Book book : allBooks) {
			logger.info("Book ==>" + book.getIsbn() + " is of type " + book.getClass().getSimpleName());
			System.out.println("Book ==>" + book.getIsbn() + " is of type " + book.getClass().getSimpleName());
			Class cls = isbnToClass.get(book.getIsbn());
			if (cls != null) {
				assertSame(cls, book.getClass());
			}
		}
	}
	
    /*
     * Setup
     */
	
	@BeforeClass
	public static void initializeOpenEJB() throws NamingException {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");

		properties.put("bookstoreDatasource", "new://Resource?type=DataSource");
		properties.put("bookstoreDatasource.JdbcDriver", "org.apache.derby.jdbc.ClientDriver");
		properties.put("bookstoreDatasource.JdbcUrl", "jdbc:derby://localhost:1527/BookStore");
		properties.setProperty("bookstoreDatasource.UserName", "guest");
		properties.setProperty("bookstoreDatasource.Password", "password");

		initialContext = new InitialContext(properties);
	}
	
	@Before
	public void getDaos() throws NamingException {
		storeDao = (StoreDao)initialContext.lookup("StoreDaoBeanLocal");
		bookDao = (BookDao)initialContext.lookup("BookDaoBeanLocal");
	}
	
	private static Logger logger = Logger.getLogger(BookStoreORMTests.class);
	private static InitialContext initialContext;
	
	// DAOs
	private StoreDao storeDao;
	private BookDao bookDao;
	

}
