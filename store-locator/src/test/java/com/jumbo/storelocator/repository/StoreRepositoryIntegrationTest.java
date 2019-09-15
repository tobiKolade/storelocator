package com.jumbo.storelocator.repository;

import com.jumbo.storelocator.Application;
import com.jumbo.storelocator.entity.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by tobi.oladimeji on 09/11/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StoreRepositoryIntegrationTest {

    @Autowired
    private StoreRepository storeRepository;
    private Store store;

    @Before
    public void setup() {
            store = new Store(
                    "s Gravendeel",
                    "3295 BD",
                    "Kerkstraat",
                    "37",
                    "",
                    "EOgKYx4XFiQAAAFJa_YYZ4At",
                    "Jumbo 's Gravendeel Gravendeel Centrum",
                    4.615551f,
                    51.778461f,
                    1,
                    true,
                    LocalTime.parse("08:00"),
                    LocalTime.parse("20:00"),
                    "SupermarktPuP",
                    true,
                     101,
                    true
            );
    }

    @Test
    public void givenStoreRepository_whenSaveAndRetrieveEntity_thenOK() {
        Store storeEntity = storeRepository.save(store);
        Store foundStore = storeRepository.findById(storeEntity.getId()).get();

        assertNotNull(foundStore);
        assertEquals(storeEntity.getComplexNumber(), foundStore.getComplexNumber());
        assertEquals(storeEntity.getStreet3(), foundStore.getStreet3());
        assertEquals(storeEntity.getStreet(), foundStore.getStreet());
    }

    @Test
    public void testStoresJsonFileInitializationOk() {
        Iterable<Store> stores = storeRepository.findAll();
        int size = ((Collection<?>)stores).size();

        assertNotNull(stores);
        assertThat(size, greaterThanOrEqualTo(2));
    }

    @Test
    public void testAfterInitialization_thenDataOk() {
        Optional<Store> storeEntity = storeRepository.findById(2L);

        assertNotNull(storeEntity);
        assertEquals(true, storeEntity.isPresent());
        assertEquals(30170, storeEntity.get().getComplexNumber());
    }
}
