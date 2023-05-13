package com.bnpp.fortis.developmentbooks.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceSummationServiceImplTest {

    private static final String BOOK_NAME = "Clean Code";
    private static final double BOOK_PRICE = 50.00;
    @Autowired
    private PriceSummationServiceImpl priceSummationServiceImpl;

    @Test
    @DisplayName("single book  should return price 50")
    void singleBookCalculatedPriceIsFifty() {

        Double actualBookPrice = priceSummationServiceImpl.calculateBookPrice(BOOK_NAME);

        assertEquals(BOOK_PRICE, actualBookPrice);
    }


}