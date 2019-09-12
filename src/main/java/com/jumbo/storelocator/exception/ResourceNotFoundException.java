package com.jumbo.storelocator.exception;

/**
 * Created by tobi.oladimeji on 09/11/2019
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String resource;
    private String resourceId;

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String resource, String resourceId) {
        super();
        this.resource = resource;
        this.resourceId = String.format("id [%s]", resourceId);
    }

    public String getResource() {
        return resource;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getMessageCode() {
        return "resource.not.found";
    }

    @Override
    public String getMessage() {
        return String.format("%s with %s not found", resource, resourceId);
    }
}
