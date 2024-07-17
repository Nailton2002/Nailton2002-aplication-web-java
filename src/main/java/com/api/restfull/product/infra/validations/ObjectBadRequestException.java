package com.api.restfull.product.infra.validations;

public class ObjectBadRequestException extends RuntimeException{

    public ObjectBadRequestException(String message){
        super(message);
    }
}
