package com.jumbo.storelocator.service;

import com.jumbo.storelocator.dao.GenericDao;
import com.jumbo.storelocator.entity.Store;
import com.jumbo.storelocator.repository.StoreRepository;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by tobi.oladimeji on 09/11/2019
 */
public class StoreServiceImplTest {

    private StoreServiceImpl target = null;
    protected @Mock StoreRepository mockStoreRepository;
    protected @Mock GenericDao mockGenericDao;
    private List<Store> stores;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        target = new StoreServiceImpl(mockStoreRepository, mockGenericDao);

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

}
