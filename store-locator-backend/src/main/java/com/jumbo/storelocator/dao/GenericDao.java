package com.jumbo.storelocator.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    <S extends T> Iterable<S> saveInBatch(List<S> entities);

    <S extends T> Iterable<S> callStoredProcedure(String procName,
                                    Class<S> resultClass,
                                    Object... args);
}
