package com.infinities.library.validator;

import com.infinities.library.models.BookModel;

public interface BookValidator {

    boolean isAvailableForUpdate(BookModel bookModel, BookModel toUpdate);

    boolean isAvailableForCreate(BookModel bookModel);
}
