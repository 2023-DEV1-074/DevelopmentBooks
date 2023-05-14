package com.bnpp.fortis.developmentbooks.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class BookGroupClassification {
    @ApiModelProperty(notes = "Group Of Classified Book", name = "listOfBooks", required = true, value = "test list of book groups ")
    private List<String> listOfBooks;

    @ApiModelProperty(notes = "Applied discount percentage of Books", name = "appliedDiscountPercentage", required = true, value = "20")
    private int appliedDiscountPercentage;

    @ApiModelProperty(notes = "Actual price of Books", name = "actualPrice", required = true, value = "100.0")
    private double actualPrice;

    @ApiModelProperty(notes = "Total Discount Amount for the Books", name = "discountAmount", required = true, value = "20")
    private double discountAmount;

    @ApiModelProperty(notes = "Number of distinct Books", name = "numberOfDistinctBooks", required = true, value = "3")
    private int numberOfDistinctBooks;
}