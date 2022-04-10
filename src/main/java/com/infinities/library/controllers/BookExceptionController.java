package com.infinities.library.controllers;

import com.infinities.library.exception.BookAlreadyExistedException;
import com.infinities.library.exception.BookNotFoundException;
import com.infinities.library.datas.Response;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionController {

    @ExceptionHandler({BookAlreadyExistedException.class, BookNotFoundException.class,
            IllegalArgumentException.class, PropertyValueException.class})
    public <T extends RuntimeException> ResponseEntity<Response> alreadyExistedBook(final T e) {
        return ResponseEntity.ok(
                Response.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }
}
