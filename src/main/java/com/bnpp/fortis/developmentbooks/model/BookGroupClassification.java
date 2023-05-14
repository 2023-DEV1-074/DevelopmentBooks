package com.bnpp.fortis.developmentbooks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class BookGroupClassification {
    private List<String> listOfBooks;
    private int appliedDiscountPercentage;
    private double actualPrice;
    private double discountAmount;
    private int numberOfDistinctBooks;
}