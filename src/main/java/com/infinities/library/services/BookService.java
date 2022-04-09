package com.infinities.library.services;

import com.infinities.library.datas.BookData;
import com.infinities.library.models.BookModel;

import java.util.List;

public interface BookService {

    BookModel createBook(final BookModel bookModel);

    BookModel updateBook(final BookModel bookModel);

    boolean deleteBook(Long isbn);

    List<BookModel> getAllBooks();

}
