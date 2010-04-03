package com.integrallis.modernjee.bookstore.dao;

import java.util.List;

import com.integrallis.modernjee.bookstore.domain.Store;

public interface StoreDao extends DataAccessObject<Long, Store> {
	List<Store> getAllStores();
	Store findByNickName(String nickName);
    Double findTotalValueOfBookForStore(Store store);
}
