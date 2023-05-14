package com.bnpp.fortis.developmentbooks.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartSummaryReportDto {
    @ApiModelProperty(notes = "Group Of Classified Book", name = "listOfBookGroupClassifications", required = true, value = "test list of book groups ")
    private List<BookGroupClassification> listOfBookGroupClassifications;

    @ApiModelProperty(notes = "Actual price of Books", name = "actualPrice", required = true, value = "100")
    private double actualPrice;

    @ApiModelProperty(notes = "Total Discount for the Books", name = "totalDiscount", required = true, value = "20")
    private double totalDiscount;

    @ApiModelProperty(notes = "BestPrice post discount for the Books", name = "costEffectivePrice", required = true, value = "80")
    private double costEffectivePrice;

}