package com.infinities.library.controllers;

import com.infinities.library.datas.BookUpdateData;
import com.infinities.library.models.BookModel;
import com.infinities.library.datas.Response;
import com.infinities.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@Slf4j
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/all")
    public ResponseEntity<Response> getAllBooks() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("books", bookService.getAllBooks()))
                        .message("fetching all books")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createBook(@RequestBody final BookModel bookModel) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("createdBook", bookService.createBook(bookModel)))
                        .message("create the book successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Response> updateBook(@RequestBody final BookUpdateData updateBook) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("updatedBook", bookService.updateBook(updateBook)))
                        .message("update the book successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Response> deleteBook(@RequestParam("isbn") final Long isbn) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("isDeletedBook", bookService.deleteBook(isbn)))
                        .status(HttpStatus.OK)
                        .build()
        );
    }

}
