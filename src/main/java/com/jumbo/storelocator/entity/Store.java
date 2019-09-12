package com.jumbo.storelocator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by tobi.oladimeji on 09/09/2019
 */
@Entity
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //I used sequence as generation type so as to support batch insert
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String uuid;
    private String addressName;
    private double longitude;
    private double latitude;
    private int complexNumber;
    private boolean showWarningMessage;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String locationType;
    private boolean collectionPoint;
    private int sapStoreId;
    private boolean active;

    protected Store() {
    }

    public Store(String city, String postalCode, String street, String street2, String street3, String uuid,
                 String addressName, double longitude, double latitude, int complexNumber,
                 boolean showWarningMessage, LocalTime openTime, LocalTime closeTime, String locationType,
                 boolean collectionPoint, int sapStoreId, boolean active) {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.street2 = street2;
        this.street3 = street3;
        this.uuid = uuid;
        this.addressName = addressName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.complexNumber = complexNumber;
        this.showWarningMessage = showWarningMessage;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.locationType = locationType;
        this.collectionPoint = collectionPoint;
        this.sapStoreId = sapStoreId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreet2() {
        return street2;
    }

    public String getStreet3() {
        return street3;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAddressName() {
        return addressName;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getComplexNumber() {
        return complexNumber;
    }

    public boolean getShowWarningMessage() {
        return showWarningMessage;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public String getLocationType() {
        return locationType;
    }

    public boolean getCollectionPoint() {
        return collectionPoint;
    }

    public int getSapStoreId() {
        return sapStoreId;
    }

    public boolean getActive() {
        return active;
    }
}
