package com.infinities.library.validator.impl;

import com.infinities.library.models.BookModel;
import com.infinities.library.validator.BookValidator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookValidatorImpl implements BookValidator {

    @Override
    public boolean isAvailableForUpdate(final BookModel bookModel, final BookModel toUpdate) {

        boolean isUpdate = false;
        if (Strings.isNotBlank(bookModel.getAuthor())) {
            toUpdate.setAuthor(bookModel.getAuthor());
            isUpdate = true;
        }

        if (Strings.isNotBlank(bookModel.getTranslator())) {
            toUpdate.setTranslator(bookModel.getTranslator());
            isUpdate = true;
        }

        if (Strings.isNotBlank(bookModel.getPublisher())) {
            toUpdate.setPublisher(bookModel.getPublisher());
            isUpdate = true;
        }

        if (Objects.nonNull(bookModel.getCurrency())) {
            toUpdate.setCurrency(bookModel.getCurrency());
            isUpdate = true;
        }

        if (Objects.nonNull(bookModel.getPrice())) {
            toUpdate.setPrice(bookModel.getPrice());
            isUpdate = true;
        }

        if (Objects.nonNull(bookModel.getPublishDate())) {
            toUpdate.setPublishDate(bookModel.getPublishDate());
            isUpdate = true;
        }

        return isUpdate;
    }

    @Override
    public boolean isAvailableForCreate(final BookModel bookModel) {
        return Objects.isNull(bookModel.getIsbn());
    }
}
