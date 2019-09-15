//package com.jumbo.storelocator.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jumbo.storelocator.Application;
//import com.jumbo.storelocator.dao.GenericDao;
//import com.jumbo.storelocator.entity.Store;
//import com.jumbo.storelocator.model.request.StoreGeoRequest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by tobi.oladimeji on 09/11/2019
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Application.class, GenericDao.class, ObjectMapper.class})
//public class StoreServiceImplTest {
//
//    @Autowired
//    private StoreServiceImpl storeService;
//
//    private List<Store> stores;
//
//    @Before
//    public void setUp() {
//        stores = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            Store store = new Store(
//                    "s Gravendeel",
//                    "3295 BD",
//                    "Kerkstraat",
//                    "37",
//                    "",
//                    "EOgKYx4XFiQAAAFJa_YYZ4At",
//                    "Jumbo 's Gravendeel Gravendeel Centrum",
//                    4.615551f,
//                    51.778461f,
//                    i + 1,
//                    true,
//                    LocalTime.parse("08:00"),
//                    LocalTime.parse("20:00"),
//                    "SupermarktPuP",
//                    true,
//                    i + 100,
//                    true
//            );
//            stores.add(store);
//        }
//    }
//}
