package com.infinities.library.exception;

public class BookAlreadyExistedException extends RuntimeException {

    public BookAlreadyExistedException(String message) {
        super(message);
    }
}
