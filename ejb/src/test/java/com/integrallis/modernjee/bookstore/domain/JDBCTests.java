package com.integrallis.modernjee.bookstore.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class JDBCTests {
	private static Logger logger = Logger.getLogger(JDBCTests.class);
	
	private static Connection connection;
	private Statement stmt;
	
	@BeforeClass
	public static void establishConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		connection = DriverManager.getConnection("jdbc:derby://localhost:1527/BookStore;create=true", "guest", "password");
	}
	
	@Test
	public void getSomeBooks() throws SQLException {
		stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT isbn, title, publishedOn, price FROM Book");
        try {
    		String isbn, title;
    		Date published;
    		Double price;
    		
    		while (rs.next()) {
    			isbn = rs.getString("isbn");
    			title = rs.getString("title");
    			published = rs.getDate("publishedOn");
    			price = rs.getDouble("price");
                logger.info("Book = " + title + ", ISBN = " + isbn + ", published = " + published + ", price = " + price);
    		}			
		} finally {
			rs.close();
		}
	}
	
	
	/**
	 * create a ResultSet object that is updatable
	 * Note that the code also makes uprs scrollable. An updatable ResultSet object does not 
	 * necessarily have to be scrollable, but when you are making changes to a result set, 
	 * you generally want to be able to move around in it. With a scrollable result set, you 
	 * can move to rows you want to change, and if the type is TYPE_SCROLL_SENSITIVE, you can 
	 * get the new value in a row after you have changed it.
	 * @throws SQLException
	 */
	@Test
	public void concurrentUpdate() throws SQLException {
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet groovyBooks = stmt.executeQuery("SELECT title, price FROM Book WHERE title like 'Groovy%'");
        groovyBooks.last();
        Double oldPrice = groovyBooks.getDouble("price");
        Double newPrice = oldPrice * 1.25;
        groovyBooks.updateDouble("price", newPrice);
        groovyBooks.updateRow();
	}
	
	@After
	public void closeStatement() throws SQLException {
		if (stmt != null) stmt.close();
	}
	
	@AfterClass
	public static void closeConnection() throws SQLException {
		if (connection != null) connection.close();
	}
}
