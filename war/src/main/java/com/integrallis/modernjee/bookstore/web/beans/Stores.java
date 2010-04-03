package com.integrallis.modernjee.bookstore.web.beans;

import java.util.List;

import javax.ejb.EJB;

import com.integrallis.modernjee.bookstore.domain.Store;
import com.integrallis.modernjee.bookstore.service.StoreService;

public class Stores {
    @EJB
    private StoreService storeService;
    

	public List<String> getAllNames() {
		return storeService.listAllStoreNames();
	}

	public List<Store> getAll() {
		return storeService.allStores();
	}
}
