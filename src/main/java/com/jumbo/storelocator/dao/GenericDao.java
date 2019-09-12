package com.jumbo.storelocator.dao;

import java.io.Serializable;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
public interface GenericDao<T, ID extends Serializable> {

    <S extends T> S persist(S entity);

    <S extends T> Iterable<S> saveInBatch(Iterable<S> entities);

    <S extends T> Iterable<S> callStoredProcedure(String procName,
                                    Class<S> resultClass,
                                    Object... args);
}
