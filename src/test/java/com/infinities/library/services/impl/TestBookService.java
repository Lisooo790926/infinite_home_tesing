package com.infinities.library.services.impl;

import com.infinities.library.datas.BookUpdateData;
import com.infinities.library.exception.BookAlreadyExistedException;
import com.infinities.library.exception.BookNotFoundException;
import com.infinities.library.models.BookModel;
import com.infinities.library.repo.BookRepository;
import com.infinities.library.validator.BookValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestBookService {

    private BookModel bookModel;
    private BookUpdateData bookUpdateData;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookValidator bookValidator;

    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(bookRepository, bookValidator);
        bookModel = new BookModel();
        bookUpdateData = new BookUpdateData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createBook_with_unavailable_model() {
        Mockito.when(bookValidator.isAvailableForCreate(bookModel)).thenReturn(false);
        bookService.createBook(bookModel);
    }

    @Test(expected = BookAlreadyExistedException.class)
    public void test_createBook_with_existed_book() {
        Mockito.when(bookValidator.isAvailableForCreate(bookModel)).thenReturn(true);
        bookModel.setName("test");
        Mockito.when(bookRepository.findByName(Mockito.anyString())).thenReturn(bookModel);
        bookService.createBook(bookModel);
    }

    @Test
    public void test_createBook_successfully_created() {
        Mockito.when(bookValidator.isAvailableForCreate(bookModel)).thenReturn(true);
        bookModel.setName("test");
        Mockito.when(bookRepository.findByName(Mockito.anyString())).thenReturn(null);
        Mockito.when(bookRepository.save(bookModel)).thenReturn(bookModel);
        BookModel book = bookService.createBook(bookModel);
        Assert.assertEquals(book, bookModel);
    }

    @Test(expected = BookNotFoundException.class)
    public void test_updateBook_not_found_any_book_with_no_empty_isbn() {

        bookUpdateData.setIsbn(1L);
        bookUpdateData.setName("test");
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(bookRepository.findByName("test")).thenReturn(null);
        bookService.updateBook(bookUpdateData);
    }

    @Test(expected = BookNotFoundException.class)
    public void test_updateBook_not_found_any_book_with_empty_isbn() {

        bookUpdateData.setName("test");
        Mockito.when(bookRepository.findByName("test")).thenReturn(null);
        bookService.updateBook(bookUpdateData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_updateBook_missing_attribute() {

        bookUpdateData.setName("test");
        Mockito.when(bookRepository.findByName("test")).thenReturn(bookModel);
        Mockito.when(bookValidator.isAvailableForUpdate(bookUpdateData, bookModel)).thenReturn(false);

        bookService.updateBook(bookUpdateData);
    }

    @Test
    public void test_updateBook_successfully_update() {

        bookUpdateData.setName("test");
        Mockito.when(bookRepository.findByName("test")).thenReturn(bookModel);
        Mockito.when(bookValidator.isAvailableForUpdate(bookUpdateData, bookModel)).thenReturn(true);
        Mockito.when(bookRepository.save(bookModel)).thenReturn(bookModel);

        final BookModel updatedBook = bookService.updateBook(bookUpdateData);
        Assert.assertEquals(updatedBook, bookModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_deleteBook_throw_empty_result() {
        Mockito.doThrow(new EmptyResultDataAccessException(0)).when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
    }

    @Test
    public void test_deleteBook_successfully() {
        Assert.assertTrue(bookService.deleteBook(1L));
    }

    @Test
    public void test_getAllBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(bookModel));
        List<BookModel> allBooks = bookService.getAllBooks();
        Assert.assertEquals(allBooks.get(0), bookModel);
    }
}
