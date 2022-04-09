package com.infinities.library.validator.impl;

import com.infinities.library.datas.BookUpdateData;
import com.infinities.library.models.BookModel;
import com.infinities.library.validator.BookValidator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookValidatorImpl implements BookValidator {

    @Override
    public boolean isAvailableForUpdate(final BookUpdateData updateBook, final BookModel toUpdate) {

        boolean isUpdate = false;
        if (Strings.isNotBlank(updateBook.getAuthor())) {
            toUpdate.setAuthor(updateBook.getAuthor());
            isUpdate = true;
        }

        if (Strings.isNotBlank(updateBook.getTranslator())) {
            toUpdate.setTranslator(updateBook.getTranslator());
            isUpdate = true;
        }

        if (Strings.isNotBlank(updateBook.getPublisher())) {
            toUpdate.setPublisher(updateBook.getPublisher());
            isUpdate = true;
        }

        if (Objects.nonNull(updateBook.getCurrency())) {
            toUpdate.setCurrency(updateBook.getCurrency());
            isUpdate = true;
        }

        if (Objects.nonNull(updateBook.getPrice())) {
            toUpdate.setPrice(updateBook.getPrice());
            isUpdate = true;
        }

        if (Objects.nonNull(updateBook.getPublishDate())) {
            toUpdate.setPublishDate(updateBook.getPublishDate());
            isUpdate = true;
        }

        return isUpdate;
    }

    @Override
    public boolean isAvailableForCreate(final BookModel bookModel) {
        return Objects.isNull(bookModel.getIsbn());
    }
}
