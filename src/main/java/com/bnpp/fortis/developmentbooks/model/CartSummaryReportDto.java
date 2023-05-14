package com.bnpp.fortis.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartSummaryReportDto {

    private List<BookGroupClassification> listOfBookGroupClassifications;

    private double actualPrice;

    private double totalDiscount;

    private double costEffectivePrice;

}