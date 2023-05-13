package com.bnpp.fortis.developmentbooks.storerepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountDetailsEnumTest {

    private static final int TOTAL_NO_OF_DISCOUNT_CATEGORY = 4;

    @Test
    public void discountDetailsEnumShouldContainFourDiscountCategory() {

        Assertions.assertThat(TOTAL_NO_OF_DISCOUNT_CATEGORY).isEqualTo(DiscountDetailsEnum.values().length);

    }
}