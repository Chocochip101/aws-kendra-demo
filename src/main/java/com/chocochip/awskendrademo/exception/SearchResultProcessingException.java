package com.chocochip.awskendrademo.exception;

public class SearchResultProcessingException extends RuntimeException {
    public SearchResultProcessingException(String message) {
        super(message);
    }

    public SearchResultProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}