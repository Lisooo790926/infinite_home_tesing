package com.infinities.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long isbn;

    @Basic(optional = false)
    @Column(unique = true)
    @NotNull(message = "Book's name could not be empty")
    private String name;

    @Basic(optional = false)
    @NotNull(message = "Author's name could not be empty")
    private String author;

    private String translator;

    @Basic(optional = false)
    @NotNull(message = "Publisher's name could not be empty")
    private String publisher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Basic(optional = false)
    @NotNull(message = "Please define the price")
    private BigDecimal price;

    @Column(length = 32, columnDefinition = "varchar(32) default 'TWD'")
    @Enumerated(EnumType.STRING)
    private CurrencyModel currency = CurrencyModel.TWD;

}
