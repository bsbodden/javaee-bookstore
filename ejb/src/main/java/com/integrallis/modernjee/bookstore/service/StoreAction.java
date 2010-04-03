package com.integrallis.modernjee.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.integrallis.modernjee.bookstore.domain.Store;

@Stateless
public class StoreAction implements StoreService {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<String> listAllStoreNames() {
		List<String> storeNames = new ArrayList<String>();
		Query query = entityManager.createQuery("SELECT s FROM Store s");
		List<Store> stores = query.getResultList();
		for (Store store : stores) {
			storeNames.add(store.getNickName());
		}
		return storeNames;
	}

	@SuppressWarnings("unchecked")
	public List<Store> allStores() {
		Query query = entityManager.createQuery("SELECT s FROM Store s");
		return query.getResultList();
	}

}
