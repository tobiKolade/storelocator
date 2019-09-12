package com.jumbo.storelocator.repository;

import com.jumbo.storelocator.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
