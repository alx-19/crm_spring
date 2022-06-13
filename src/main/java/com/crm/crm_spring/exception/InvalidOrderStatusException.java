package com.crm.crm_spring.exception;

public class InvalidOrderStatusException extends RuntimeException {

    public InvalidOrderStatusException(String message) {
        super(message);
    }

     public InvalidOrderStatusException() {
        super("Cannot set the desired status.");
     }
}
