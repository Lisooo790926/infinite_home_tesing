package com.infinities.library.validator.impl;

import com.infinities.library.datas.BookUpdateData;
import com.infinities.library.models.BookModel;
import com.infinities.library.models.CurrencyModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestBookValidator {

    private BookModel bookModel;
    private BookUpdateData bookUpdateData;

    @InjectMocks
    private BookValidatorImpl bookValidator;

    @Before
    public void setup() {
        bookModel = new BookModel();
        bookUpdateData = new BookUpdateData();
    }

    @Test
    public void test_isAvailableForUpdate_successfully_update() {
        bookUpdateData.setAuthor("testA");
        bookUpdateData.setTranslator("testT");
        bookUpdateData.setPublisher("testP");
        bookUpdateData.setCurrency(CurrencyModel.TWD);
        bookUpdateData.setPrice(BigDecimal.ONE);
        bookUpdateData.setPublishDate(new Date());

        Assert.assertTrue(bookValidator.isAvailableForUpdate(bookUpdateData, bookModel));
        Assert.assertEquals(bookModel.getAuthor(), "testA");
        Assert.assertEquals(bookModel.getTranslator(), "testT");
        Assert.assertEquals(bookModel.getPublisher(), "testP");
        Assert.assertEquals(bookModel.getCurrency(), CurrencyModel.TWD);
        Assert.assertEquals(bookModel.getPrice(), BigDecimal.ONE);
        Assert.assertNotNull(bookModel.getPublishDate());
    }

    @Test
    public void test_isAvailableForUpdate_with_no_attribute() {
        Assert.assertFalse(bookValidator.isAvailableForUpdate(bookUpdateData, bookModel));
    }

    @Test
    public void test_isAvailableForCreate() {
        Assert.assertTrue(bookValidator.isAvailableForCreate(bookModel));
        bookModel.setIsbn(1L);
        Assert.assertFalse(bookValidator.isAvailableForCreate(bookModel));
    }


}
