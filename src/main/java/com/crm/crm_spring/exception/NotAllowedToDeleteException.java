package com.crm.crm_spring.exception;

public class NotAllowedToDeleteException extends RuntimeException{

    public NotAllowedToDeleteException(String message) {
        super(message);
    }

}
