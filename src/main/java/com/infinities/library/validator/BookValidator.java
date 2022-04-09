package com.infinities.library.validator;

import com.infinities.library.datas.BookUpdateData;
import com.infinities.library.models.BookModel;

public interface BookValidator {

    boolean isAvailableForUpdate(BookUpdateData updateBook, BookModel toUpdate);

    boolean isAvailableForCreate(BookModel bookModel);
}
