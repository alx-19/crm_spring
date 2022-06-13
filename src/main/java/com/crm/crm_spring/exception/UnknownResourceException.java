package com.crm.crm_spring.exception;

public class UnknownResourceException extends RuntimeException {

    public UnknownResourceException() {
        super("Ressource inconnue.");
    }

    public UnknownResourceException(String message) {
        super(message);
    }
}
