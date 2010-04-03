package com.integrallis.modernjee.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Inventory implements Serializable {


	@Id
	@GeneratedValue
	@Column(name = "INVENTORY_ID")
	private Long id;
	
	@ManyToOne
	private Book book;

	@ManyToOne
	private Store store;
	
	private Integer quantity;

	public Inventory() {
	}

	public Inventory(Store store, int quantity) {
		setStore(store);
		setQuantity(quantity);
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean equals(Object object) {
		// short circuits
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (!(object instanceof Inventory))
			return false;
		final Inventory inventory = (Inventory) object;

		return new EqualsBuilder().append(book, inventory.getBook()).append(
				store, inventory.getStore()).isEquals();
	}

	public int hashcode() {
		return new HashCodeBuilder(19, 23).append(book).append(store)
				.toHashCode();
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Store getStore() {
		return store;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7563432120978482291L;
}
