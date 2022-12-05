package com.jumbo.storelocator.service;

import com.jumbo.storelocator.entity.StoreGeoResult;
import com.jumbo.storelocator.model.request.StoreGeoRequest;

import java.io.IOException;

public class CodeScanningViolationStoreImpl implements StoreService {

    public void initStores() throws IOException {

    }

    public Iterable<StoreGeoResult> findNearestStores(StoreGeoRequest request) {
        return null;
    }

    public Iterable<StoreGeoResult> findNearestActiveStores(StoreGeoRequest request) {
        return null;
    }

    public Iterable<StoreGeoResult> findNearestOpenStores(StoreGeoRequest request) {
        return null;
    }
}
