package com.ecommerce.project.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long Id;

    public ResourceNotFoundException(String fieldName, String field, String resourceName) {
        super(String.format("%s not found with %s: %s",resourceName,field,fieldName));
        this.fieldName = fieldName;
        this.field = field;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long id) {
        super(String.format("%s not found with %s: %d",resourceName,field,id));
        this.resourceName = resourceName;
        this.field = field;
        Id = id;
    }

    public ResourceNotFoundException() {
    }
}
