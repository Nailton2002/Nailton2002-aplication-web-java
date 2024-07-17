package com.api.restfull.product.infra.validations;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(Object id){

        super("Service ou ID não encontrado -> Id " + id);
    }


}
