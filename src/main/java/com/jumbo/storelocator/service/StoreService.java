package com.jumbo.storelocator.service;

import com.jumbo.storelocator.entity.StoreGeoResult;
import com.jumbo.storelocator.model.request.StoreGeoRequest;

import java.io.IOException;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
public interface StoreService {

    void initStores() throws IOException;

    Iterable<StoreGeoResult> findNearestStores(StoreGeoRequest request);

    Iterable<StoreGeoResult> findNearestActiveStores(StoreGeoRequest request);

    Iterable<StoreGeoResult> findNearestOpenStores(StoreGeoRequest request);
}
