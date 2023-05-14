package com.bnpp.fortis.developmentbooks.storerepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountDetailsEnumTest {

    private static final int TOTAL_NO_OF_DISCOUNT_CATEGORY = 4;

    @Test
    void discountDetailsEnumShouldContainFourDiscountCategory() {

        assertEquals(TOTAL_NO_OF_DISCOUNT_CATEGORY,DiscountDetailsEnum.values().length);

    }
}