package com.bnpp.fortis.developmentbooks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {


    private String bookTitle;
    private String author;
    private double price;

}