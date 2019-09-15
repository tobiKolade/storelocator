package com.jumbo.storelocator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.storelocator.dao.GenericDao;
import com.jumbo.storelocator.entity.Store;
import com.jumbo.storelocator.entity.StoreGeoResult;
import com.jumbo.storelocator.model.StoreProcessModel;
import com.jumbo.storelocator.model.request.StoreGeoRequest;
import com.jumbo.storelocator.model.util.ConstantUtil;
import com.jumbo.storelocator.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Value("${stores.file}")
    private Resource resource;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GenericDao dao;

    /**
     * This method is called at startup of the app
     * It loads all jumbo stores into the db, provided this data has not been loaded before
     */
    public void initStores() throws IOException {
        if(null != storeRepository.findFirstId())
            return;

        TypeReference<List<StoreProcessModel>> typeReference = new TypeReference<List<StoreProcessModel>>(){};
        JsonNode treeNode = objectMapper.readTree(resource.getInputStream());
        JsonNode storesNode = treeNode.findPath(ConstantUtil.STORE_NODE_NAME);
        List<StoreProcessModel> jsonStores = objectMapper.readValue(storesNode.toString(), typeReference);

        List<Store> stores = new ArrayList<>();
        jsonStores.stream()
                .forEach( storeProcessModel -> {
                    Store store = new Store(
                            storeProcessModel.getCity(),
                            storeProcessModel.getPostalCode(),
                            storeProcessModel.getStreet(),
                            storeProcessModel.getStreet2(),
                            storeProcessModel.getStreet3(),
                            storeProcessModel.getUuid(),
                            storeProcessModel.getAddressName(),
                            storeProcessModel.getLongitude(),
                            storeProcessModel.getLatitude(),
                            storeProcessModel.getComplexNumber(),
                            storeProcessModel.getShowWarningMessage(),
                            storeProcessModel.getOpenTime(storeProcessModel.getTodayOpen()),
                            storeProcessModel.getCloseTime(storeProcessModel.getTodayClose()),
                            storeProcessModel.getLocationType(),
                            storeProcessModel.getCollectionPoint(),
                            storeProcessModel.getSapStoreId(),
                            storeProcessModel.isActive(storeProcessModel.getTodayOpen())
                    );
                    stores.add(store);
                });
        dao.saveInBatch(stores);
    }

    /**
     * This method finds the 5 closest stores to the given position using haversine formula
     * considering that the earth is not a linear plane,  otherwise distance between 2 points would have sufficed
     * @param request
     * @return
     */
    @Cacheable(value = ConstantUtil.STORE_CACHE_NAME, key = "#request.longitude + '|' + #request.latitude")
    public Iterable<StoreGeoResult> findNearestStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_stores",
                StoreGeoResult.class,
                request.getLatitude(), request.getLongitude());
    }

    /**
     * This method finds the 5 closest active stores to the given position using haversine formula
     * I marked all stores with todayOpen value 'Gesloten' as inactive
     * @param request
     * @return
     */
    @Cacheable(value = ConstantUtil.ACTIVE_STORE_CACHE_NAME, key = "#request.longitude + '|' + #request.latitude")
    public Iterable<StoreGeoResult> findNearestActiveStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_active_stores",
                StoreGeoResult.class,
                request.getLatitude(), request.getLongitude());
    }

    /**
     * This method finds the 5 closest active and open stores to the given position using haversine formula
     * Closed stores relative to the current local time are filtered out here
     * @param request
     * @return
     */
    @Cacheable(value = ConstantUtil.OPEN_STORE_CACHE_NAME, key = "#request.longitude + '|' + #request.latitude")
    public Iterable<StoreGeoResult> findNearestOpenStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_open_stores",
                StoreGeoResult.class,
                request.getLatitude(), request.getLongitude(),
                LocalTime.now());
    }

    /**
     * This is to clear the cache hourly
     * This wouldn't be needed if I'm using a cachemanager like redis, hazelcast etc
     * where I can set TTL
     * An update or create would also require cache clearing as it would invalidate the cached results
     */
    @CacheEvict(allEntries = true)
    @Scheduled(cron = "0 0 */1 * * ?")
    public void clearCache() {
        int s = 0;
    }
}
