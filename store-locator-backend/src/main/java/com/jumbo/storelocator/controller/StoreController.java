package com.jumbo.storelocator.controller;

import com.jumbo.storelocator.entity.StoreGeoResult;
import com.jumbo.storelocator.model.request.StoreGeoRequest;
import com.jumbo.storelocator.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping(value = "/nearest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<StoreGeoResult> nearest(@Validated StoreGeoRequest request) {
        String book = "abc";
        return storeService.findNearestStores(request);
    }

    @GetMapping(value = "/nearest/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<StoreGeoResult> active(@Validated StoreGeoRequest request) {
        return storeService.findNearestActiveStores(request);
    }

    @GetMapping(value = "/nearest/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<StoreGeoResult> open(@Validated StoreGeoRequest request) {
        return storeService.findNearestOpenStores(request);
    }
}
