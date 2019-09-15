package com.jumbo.storelocator.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by tobi.oladimeji on 09/14/2019
 */
@Entity
public class StoreGeoResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String addressName;
    private String street;
    private String street2;
    private LocalTime openTime;
    private LocalTime closeTime;
    private double longitude;
    private double latitude;
    private double distance;

    public Long getId() {
        return id;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getStreet() {
        return street;
    }

    public String getStreet2() {
        return street2;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getDistance() {
        return distance;
    }
}
