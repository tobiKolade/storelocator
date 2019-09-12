package com.jumbo.storelocator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by tobi.oladimeji on 09/12/2019
 */
public class StoreProcessModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String CLOSED_STORE = "Gesloten";
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
    private String todayOpen;
    private String todayClose;
    private String locationType;
    private boolean collectionPoint;
    private int sapStoreId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTodayOpen() {
        return todayOpen;
    }

    public String getTodayClose() {
        return todayClose;
    }

    public LocalTime getOpenTime(String todayOpen) {
        LocalTime openTime = CLOSED_STORE.equalsIgnoreCase(todayOpen) ?
                null : LocalTime.parse(todayOpen);
        return openTime;
    }

    public LocalTime getCloseTime(String todayClose) {
        LocalTime closeTime = CLOSED_STORE.equalsIgnoreCase(todayClose) ?
                null : LocalTime.parse(todayClose);
        return closeTime;
    }

    public boolean isActive(String todayOpen) {
        boolean status = CLOSED_STORE.equalsIgnoreCase(todayOpen) ?
                false : true;
        return status;
    }

    public String getLocationType() {
        return locationType;
    }

    public boolean getCollectionPoint() {
        return collectionPoint;
    }

    @JsonProperty("sapStoreID")
    public int getSapStoreId() {
        return sapStoreId;
    }
}
