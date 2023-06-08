package com.lungvision.lungdiseasexrayclassificationbe.exception;

public class ServiceUnavailable extends RuntimeException{
    public ServiceUnavailable(String message) {
        super(message);
    }
}
