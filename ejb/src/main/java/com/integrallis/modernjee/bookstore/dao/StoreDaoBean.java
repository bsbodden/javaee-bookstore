package com.integrallis.modernjee.bookstore.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Query;

import com.integrallis.modernjee.bookstore.domain.Store;

@Stateful
public class StoreDaoBean extends BaseDataAccessObject<Long, Store> implements StoreDao {

	public Store findByNickName(String nickName) {
		Query query = entityManager.createNamedQuery(Store.QUERY_FIND_BY_NICKNAME);
		query.setParameter("nickname", nickName);
		return (Store) query.getSingleResult();
	}

	public Double findTotalValueOfBookForStore(Store store) {
		Query query = entityManager.createNamedQuery(Store.QUERY_FIND_TOTAL_INVENTORY_VALUE_FOR_STORE);
		query.setParameter("store", store);
		return (Double) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Store> getAllStores() {
		Query query = entityManager.createQuery("SELECT s FROM Store s");
		return (List<Store>)query.getResultList();
	}



}
