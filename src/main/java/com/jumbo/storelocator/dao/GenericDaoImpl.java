package com.jumbo.storelocator.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tobi.oladimeji on 09/10/2019
 */
@Repository
@Transactional
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    private static final Logger logger = Logger.getLogger(GenericDaoImpl.class.getName());
    private int batchSize;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GenericDaoImpl(@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") int batchSize,
                          EntityManager entityManager) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public <S extends T> Iterable<S> callStoredProcedure(String procName,
                                                         Class<S> resultClass,
                                                         Object... args) {
        String spBindParams = (args.length == 0) ? "" : "?" + StringUtils.repeat(",?", args.length - 1);

        Query call = entityManager.createNativeQuery(
                String.format("execute %s %s", procName, spBindParams),
                resultClass
        );

        int position = 0;
        for(Object arg : args) {
            call.setParameter(++position, arg);
        }

        return call.getResultList();
    }

    @Override
    public <S extends T> S persist(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveInBatch(Iterable<S> entities) {

        int count = 0;
        List<S> result = new ArrayList<>();

        for(S entity : entities) {
            result.add(persist(entity));

            count++;

            //Flush a batch of inserts and release memory
            if(count % batchSize == 0 && count > 0) {
                logger.log(Level.INFO,
                        "Flushing the EntityManager containing {0} entities ...", count);
                entityManager.flush();
                entityManager.clear();
                count = 0;
            }
        }

        if(count > 0) {
            logger.log(Level.INFO,
                    "Flushing the remaining {0} entities ...", count);

            entityManager.flush();
            entityManager.clear();
        }

        return result;
    }

//    protected EntityManager getEntityManager() {
//        return entityManager;
//    }
}
