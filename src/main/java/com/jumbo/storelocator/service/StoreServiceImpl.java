package com.jumbo.storelocator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.storelocator.dao.GenericDao;
import com.jumbo.storelocator.entity.Store;
import com.jumbo.storelocator.model.StoreProcessModel;
import com.jumbo.storelocator.model.request.StoreGeoRequest;
import com.jumbo.storelocator.repository.StoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Value("${stores.file}")
    private Resource resourceFile;

    private final String STORE_NODE_NAME = "stores";

    @Autowired
    private ObjectMapper objectMapper;

    private StoreRepository storeRepository;
    private GenericDao dao;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, GenericDao dao) {
        this.storeRepository = storeRepository;
        this.dao = dao;
    }

    //This only runs if the stores table is empty
    public void initStores() throws IOException {
        if(storeRepository.count() > 0)
            return;
        TypeReference<List<StoreProcessModel>> typeReference = new TypeReference<List<StoreProcessModel>>(){};
        JsonNode treeNode = objectMapper.readTree(resourceFile.getFile());
        JsonNode storesNode = treeNode.findPath(STORE_NODE_NAME);
        List<StoreProcessModel> storesModel = objectMapper.readValue(storesNode.toString(), typeReference);

        List<Store> stores = new ArrayList<>();
        storesModel.stream()
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
        save(stores);
    }

    @Override
    public Iterable<Store> save(List<Store> stores) {
        return dao.saveInBatch(stores);
    }

    public Iterable<Store> findNearestStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_stores",
                Store.class,
                request.getLatitude(), request.getLongitude());
    }

    public Iterable<Store> findNearestActiveStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_active_stores",
                Store.class,
                request.getLatitude(), request.getLongitude());
    }

    public Iterable<Store> findNearestOpenStores(StoreGeoRequest request) {
        return dao.callStoredProcedure("find_closest_open_stores",
                Store.class,
                request.getLatitude(), request.getLongitude(),
                LocalTime.now());
    }
}
