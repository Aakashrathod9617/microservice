package com.Icwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

        super("Resource Not Found on server");
    }

    public ResourceNotFoundException(String massege){
        super(massege);
    }
}
