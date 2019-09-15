package com.jumbo.storelocator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.storelocator.Application;
import com.jumbo.storelocator.dao.GenericDao;
import com.jumbo.storelocator.entity.Store;
import com.jumbo.storelocator.repository.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, GenericDao.class, ObjectMapper.class})
public class StoreServiceIntegrationTest {

    @Autowired
    private StoreService storeService;

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
                null,
                null,
                "SupermarktPuP",
                false,
                101,
                false
        );
    }

    @Test
    public void testSaveAndRetrieveEntityOk() {
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
    public void testDataValidAfterInititialization() {
        Optional<Store> storeEntity = storeRepository.findById(2L);

        assertNotNull(storeEntity);
        assertEquals(true, storeEntity.isPresent());
        assertEquals(30170, storeEntity.get().getComplexNumber());
    }

    @Test
    public void testValidFirstIdAfterInitialization() {
        Long id = storeRepository.findFirstId();

        assertNotNull(id);
        assertEquals(1, id.longValue());
    }
}
