package org.example.services;

public class ServiceException extends Exception{
    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

}
