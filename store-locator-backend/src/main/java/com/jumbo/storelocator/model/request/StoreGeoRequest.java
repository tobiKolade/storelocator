package com.jumbo.storelocator.model.request;

import javax.validation.constraints.NotNull;

public class StoreGeoRequest {

    @NotNull(message = "{field.required}")
    private Float longitude;

    @NotNull(message = "{field.required}")
    private Float latitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
