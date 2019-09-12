package com.jumbo.storelocator.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tobi.oladimeji on 09/12/2019
 */
public class ModelDeserializationTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testStoreProcessModelDeserialization() throws IOException {
        String json = "{" +
                "\"city\": \"Alkmaar\"," +
                "\"postalCode\": \"1824 XT\"," +
                "\"street\": \"Muiderwaard\"," +
                "\"street2\": \"416\"," +
                "\"street3\": \"\"," +
                "\"addressName\": \"Jumbo Alkmaar Duijvelshoff\"," +
                "\"uuid\":\"V7cKYx4X0QUAAAFMTmYM5CXj\"," +
                "\"longitude\": 4.749492," +
                "\"latitude\": 52.645601," +
                "\"complexNumber\": 33282," +
                "\"showWarningMessage\": true," +
                "\"todayOpen\": \"Gesloten\"," +
                "\"locationType\": \"Supermarkt\"," +
                "\"sapStoreID\": 3574," +
                "\"todayClose\": \"Gesloten\"" +
                "}";

        StoreProcessModel data = mapper.readValue(json, StoreProcessModel.class);

        assertNotNull(data);
        assertEquals(3574, data.getSapStoreId());
        assertEquals("Gesloten", data.getTodayOpen());
        assertEquals(false, data.getCollectionPoint());
        assertEquals(false, data.isActive(data.getTodayOpen()));
        assertNull(data.getOpenTime(data.getTodayOpen()));
        assertNull(data.getCloseTime(data.getTodayClose()));
    }
}
