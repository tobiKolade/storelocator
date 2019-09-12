package com.jumbo.storelocator.dao;

import com.jumbo.storelocator.entity.Store;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by tobi.oladimeji on 09/11/2019
 */
public class GenericDaoImplTest {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private List<Store> stores;
    private GenericDao dao;

    protected @Mock EntityManager mockEntityManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        dao = new GenericDaoImpl(batchSize, mockEntityManager);
        stores = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Store store = new Store(
                    "s Gravendeel",
                    "3295 BD",
                    "Kerkstraat",
                    "37",
                    "",
                    "EOgKYx4XFiQAAAFJa_YYZ4At",
                    "Jumbo 's Gravendeel Gravendeel Centrum",
                    4.615551d,
                    51.778461d,
                    i + 1,
                    true,
                    LocalTime.parse("08:00"),
                    LocalTime.parse("20:00"),
                    "SupermarktPuP",
                    true,
                    i + 100,
                    true
            );

            stores.add(store);
        }
    }

    @Test
    public void memoryClearsAtBatchLimit_whenSavingBatch() {

        Iterable<Store> savedStores = dao.saveInBatch(stores);
        int size = ((Collection<?>)savedStores).size();

        //verify that
        Mockito.verify(mockEntityManager, Mockito.times(10)).flush();
        Mockito.verify(mockEntityManager, Mockito.times(10)).clear();

        assertNotNull(savedStores);
        assertThat(size, greaterThanOrEqualTo(1000));
    }

    @Test
    public void noErrorThrown_whenEmptyRecords() {
        Iterable<Store> result = dao.saveInBatch(new ArrayList());
        assertNotNull(result);
        Mockito.verify(mockEntityManager, Mockito.times(0)).flush();
        Mockito.verify(mockEntityManager, Mockito.times(0)).clear();
    }
}
