package com.integrallis.modernjee.bookstore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.CollectionOfElements;

@NamedQueries({
	@NamedQuery(
		name = Book.QUERY_FIND_BY_ISBN,
		query = "SELECT b FROM Book b WHERE b.isbn=:isbn"
	),
	@NamedQuery(
			name = Book.QUERY_FIND_ALL_COSTING_MORE_THAN,
			query = "SELECT b FROM Book b WHERE b.price > :price"
		),
	@NamedQuery(
		name = Book.QUERY_FIND_PUBLISHED_BETWEEN_DATES,
		query = "SELECT b FROM Book b WHERE b.publishedOn BETWEEN :start AND :end"
	)}
)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Book implements Serializable {
	public final static String QUERY_FIND_BY_ISBN = "Book.findByISBN";
	public final static String QUERY_FIND_PUBLISHED_BETWEEN_DATES = "Book.findPublishedBetweenDates";
	public static final String QUERY_FIND_ALL_COSTING_MORE_THAN = "Book.findAllCostingMoreThan";
	
	@Id
	@GeneratedValue
	@Column(name = "BOOK_ID")
	private Long id;
	
	private String isbn;
	private String title;
	private Date publishedOn;
	private Double price;
	private int version = -1;

    @CollectionOfElements(fetch = FetchType.EAGER)
    @JoinTable(name = "BOOKAUTHORS", joinColumns = {@JoinColumn(name = "BOOK_ID")})
    @Column(name = "LASTNAME")
	private Set<String> authors = new HashSet<String>();
	
	@OneToMany(targetEntity = Inventory.class, mappedBy = "book")
	private Set<Inventory> inventoryRecords = new HashSet<Inventory>();

	public Book() {
	}

	public Book(String isbn, String title, Date publishedOn, Double price) {
		this.isbn = isbn;
		this.title = title;
		this.publishedOn = publishedOn;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(Date publishedOn) {
		this.publishedOn = publishedOn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}

	@SuppressWarnings("unused")
	private void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public Set<Inventory> getInventoryRecords() {
		return inventoryRecords;
	}

	public void setInventoryRecords(Set<Inventory> inventoryRecords) {
		this.inventoryRecords = inventoryRecords;
	}

	public void addInventoryRecord(Store store, int quantity) {
		Inventory inventory = new Inventory(store, quantity);
		addInventoryRecord(inventory);
	}

	public void addInventoryRecord(Inventory inventory) {
		inventory.setBook(this);
		getInventoryRecords().add(inventory);
	}

	/*
	 * Implementation of equals using Business Key Equality
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		// short circuits
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (!(object instanceof Book))
			return false;
		final Book book = (Book) object;

		return new EqualsBuilder().append(isbn, book.getIsbn()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(isbn).toHashCode();
	}

	/*
	 * A good toString makes testing/debugging much easier
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("isbn", isbn)
				.append("title", title).append("publishedOn", publishedOn)
				.append("price", price).toString();
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6713650160883679980L;
}
