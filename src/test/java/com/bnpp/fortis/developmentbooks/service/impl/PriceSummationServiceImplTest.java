package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceSummationServiceImplTest {

    private static final String BOOK_NAME = "Clean Code";
    private static final String FIRST_BOOK_NAME = "Clean Code";
    private static final String SECOND_BOOK_NAME = "The Clean Coder";
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final double BOOK_PRICE = 50.00;
    private static final double TWO_DIFF_BOOK_EXPECTED_PRICE_WITH_2_PER_DISCOUNT = 95.0;


    List<BookCartDto> bookCartDtoList;

    @Autowired
    private PriceSummationServiceImpl priceSummationServiceImpl;

    @BeforeEach
    void setup() {
        bookCartDtoList = new ArrayList<>();
    }

    @ParameterizedTest
    @CsvSource({"1,50", "2,100", "3,150", "4,200", "10,500"})
    @DisplayName("should return price based on quantity")
    void shouldReturnPriceBasedOnQuantity(int quantity, double expectedPrice) {
        BookCartDto bookCartDto = new BookCartDto(FIRST_BOOK_NAME, quantity);

        bookCartDtoList.add(bookCartDto);

        Double actualBookPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);

        assertEquals(expectedPrice, actualBookPrice);

    }


    @Test
    @DisplayName("Two different listOfBooks should get 5% discount")
    void twoDifferentBooksShouldReturnPriceNinetyFive() {

        BookCartDto firstBook = new BookCartDto(FIRST_BOOK_NAME, ONE);
        BookCartDto secondBook = new BookCartDto(SECOND_BOOK_NAME, ONE);

        bookCartDtoList.add(firstBook);
        bookCartDtoList.add(secondBook);

        Double actualBookPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);

        assertEquals(TWO_DIFF_BOOK_EXPECTED_PRICE_WITH_2_PER_DISCOUNT, actualBookPrice);
    }
}