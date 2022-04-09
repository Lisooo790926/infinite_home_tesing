package com.infinities.library.datas;

import com.infinities.library.models.CurrencyModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookUpdateData {

    private Long isbn;
    private String name;
    private String author;
    private String translator;
    private String publisher;
    private Date publishDate;
    private BigDecimal price;
    private CurrencyModel currency;
}
