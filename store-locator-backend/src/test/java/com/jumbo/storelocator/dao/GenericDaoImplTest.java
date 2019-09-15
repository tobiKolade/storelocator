package com.jumbo.storelocator.dao;

import com.jumbo.storelocator.entity.Store;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GenericDaoImplTest {

    private List<Store> stores;
    private GenericDao dao;

    protected @Mock EntityManager mockEntityManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        dao = new GenericDaoImpl(100, mockEntityManager);
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
                    4.615551f,
                    51.778461f,
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
    public void testMemoryClearedAtBatchLimit() {
        Iterable<Store> savedStores = dao.saveInBatch(stores);

        int size = ((Collection<?>)savedStores).size();

        //verify that 10 batch calls were made since batch size is 100 and I have 1000 stores
        Mockito.verify(mockEntityManager, Mockito.times(10)).flush();
        Mockito.verify(mockEntityManager, Mockito.times(10)).clear();

        assertNotNull(savedStores);
        assertThat(size, greaterThanOrEqualTo(1000));
    }

    @Test
    public void testNoErrorWhenEmptyRecords() {
        Iterable<Store> savedStores = dao.saveInBatch(new ArrayList());
        int size = ((Collection<?>)savedStores).size();

        //Verify that no batch call was made
        Mockito.verify(mockEntityManager, Mockito.times(0)).flush();
        Mockito.verify(mockEntityManager, Mockito.times(0)).clear();

        assertNotNull(savedStores);
        assertThat(size, greaterThanOrEqualTo(0));
    }
}
