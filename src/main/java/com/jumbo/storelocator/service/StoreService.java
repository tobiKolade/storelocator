package com.jumbo.storelocator.service;

import com.jumbo.storelocator.entity.Store;
import com.jumbo.storelocator.model.request.StoreGeoRequest;

import java.io.IOException;
import java.util.List;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
public interface StoreService {

    Iterable<Store> save(List<Store> stores);

    void initStores() throws IOException;

    Iterable<Store> findNearestStores(StoreGeoRequest request);

    Iterable<Store> findNearestActiveStores(StoreGeoRequest request);

    Iterable<Store> findNearestOpenStores(StoreGeoRequest request);
}
