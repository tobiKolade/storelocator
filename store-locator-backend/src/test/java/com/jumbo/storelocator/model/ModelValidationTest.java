package com.jumbo.storelocator.model;

import com.jumbo.storelocator.model.request.StoreGeoRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ModelValidationTest {
    private Validator validator;

    @Before
    public void  setUp() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.afterPropertiesSet();
        validator = bean.getValidator();
    }

    @Test
    public void testStoreGeoLocationRequestWithViolations() {
        StoreGeoRequest request = new StoreGeoRequest();

        Set<ConstraintViolation<StoreGeoRequest>> violations
                = validator.validate(request);

        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void testStoreGeoLocationRequestWithoutViolations() {
        StoreGeoRequest request = new StoreGeoRequest();
        request.setLatitude(80.0f);
        request.setLongitude(-10.0f);

        Set<ConstraintViolation<StoreGeoRequest>> violations
                = validator.validate(request);

        Assert.assertEquals(0, violations.size());
    }
}
