package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceSummationServiceImplTest {

    private static final String BOOK_NAME = "Clean Code";
    private static final double BOOK_PRICE = 50.00;

    private static final int ONE = 1;

    @Autowired
    private PriceSummationServiceImpl priceSummationServiceImpl;

    @Test
    @DisplayName("single book  should return price 50")
    void singleBookCalculatedPriceIsFifty() {

        BookCartDto bookDto = new BookCartDto(BOOK_NAME, ONE);

        Double actualBookPrice = priceSummationServiceImpl.calculateBookPrice(bookDto);

        assertEquals(BOOK_PRICE, actualBookPrice);
    }

    @ParameterizedTest
    @CsvSource({"1,50", "2,100", "3,150", "4,200", "10,500"})
    @DisplayName("should return price based on quantity")
    void shouldReturnPriceBasedOnQuantity(int quantity, double expectedPrice) {
        BookCartDto bookDto = new BookCartDto(BOOK_NAME, quantity);

        Double actualBookPrice = priceSummationServiceImpl.calculateBookPrice(bookDto);

        assertEquals(expectedPrice, actualBookPrice);
    }


}