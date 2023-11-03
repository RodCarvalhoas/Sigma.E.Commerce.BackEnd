package com.Sigma.SigmaBackEnd.excepitons;

public class DataIntegrityViolationException extends RuntimeException {

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}