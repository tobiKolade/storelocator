package com.jumbo.storelocator.repository;

import com.jumbo.storelocator.entity.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StoreRepository extends CrudRepository<Store, Long> {

    @Query(value = "SELECT TOP 1 id from Store", nativeQuery = true)
    Long findFirstId();
}
