package com.integrallis.modernjee.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@NamedQueries({
	@NamedQuery(
		name = Store.QUERY_FIND_BY_NICKNAME,
		query = "SELECT s FROM Store s WHERE nickName=:nickName"
	),
	@NamedQuery(
		name = Store.QUERY_FIND_TOTAL_INVENTORY_VALUE_FOR_STORE,
		query = "SELECT SUM(i.quantity * b.price) FROM Book b, Inventory i WHERE i.book = b AND i.store = :store"
	)}
)
@Entity
public class Store implements Serializable {
	public final static String QUERY_FIND_BY_NICKNAME = "Store.findByNickName";
	public final static String QUERY_FIND_TOTAL_INVENTORY_VALUE_FOR_STORE = "Store.findTotalValueOfBookForStore";
	
	@Id
	@GeneratedValue
	@Column(name = "STORE_ID")
	private Long id;
	
	private String nickName;
	
	private Address address;

	public Store() {
	}

	public Store(String nickName, String street1, String city, String state,
			String zipCode) {
		this.nickName = nickName;
		this.address = new Address(street1, null, city, state, zipCode);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
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
		if (!(object instanceof Store))
			return false;
		final Store store = (Store) object;

		return new EqualsBuilder().append(nickName, store.getNickName())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(nickName).toHashCode();
	}

	/*
	 * A good toString makes testing/debugging much easier
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("nickName",
				nickName)
				.toString();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
