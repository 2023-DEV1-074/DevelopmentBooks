package com.bnpp.fortis.developmentbooks.storerepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountDetailsEnum {
    TWO_DIFFERENT_BOOKS(2, 5), THREE_DIFFERENT_BOOKS(3, 10),
    FOUR_DIFFERENT_BOOKS(4, 20),
    FIVE_DIFFERENT_BOOKS(5, 25);


    private int numberOfDistinctItems;
    private int discountPercentage;
}
