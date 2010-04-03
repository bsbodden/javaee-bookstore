package com.integrallis.modernjee.bookstore.service;

import java.util.List;

import javax.ejb.Local;

import com.integrallis.modernjee.bookstore.domain.Store;

@Local
public interface StoreService {
    List<String> listAllStoreNames();
    List<Store> allStores();
}
