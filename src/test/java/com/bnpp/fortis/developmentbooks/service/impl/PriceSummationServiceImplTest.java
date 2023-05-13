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
    private static final String THIRD_BOOK_NAME = "Clean Architecture";
    private static final String FOURTH_BOOK_NAME = "Test-Driven Development By Example";

    private static final String FIFTH_BOOK_NAME = "Working Effectively With Legacy Code";
    private static final double FIVE_DISTINCT_BOOKS_PRICE_WITH_TWENTYFIVE_PERCENTAGE_DISCOUNT = 187.50;

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final double BOOK_PRICE = 50.00;
    private static final double TWO_DIFF_BOOK_EXPECTED_PRICE_WITH_2_PER_DISCOUNT = 95.0;

    private static final double THREE_DIFF_BOOK_EXPECTED_PRICE_WITH_10_PER_DISCOUNT = 135.0;

    private static final double FOUR_DISTINCT_BOOKS_PRICE_WITH_TWENTY_PERCENTAGE_DISCOUNT = 160.00;
    private static final double TWO_DISTINCT_AND_ONE_SEPARATE_BOOK_WITH_DISCOUNT = 145.00;

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

    @Test
    @DisplayName("Three different listOfBooks should get 10% discount")
    void threeDifferentBooksShouldReturnOneHundredAndThirtyFive() {


        BookCartDto firstBook = new BookCartDto(FIRST_BOOK_NAME, ONE);
        BookCartDto secondBook = new BookCartDto(SECOND_BOOK_NAME, ONE);
        BookCartDto thirdBook = new BookCartDto(THIRD_BOOK_NAME, ONE);


        bookCartDtoList.add(firstBook);
        bookCartDtoList.add(secondBook);
        bookCartDtoList.add(thirdBook);

        Double actualPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);

        assertEquals(THREE_DIFF_BOOK_EXPECTED_PRICE_WITH_10_PER_DISCOUNT, actualPrice);
    }


    @Test
    @DisplayName("Four different listOfBooks should get 20% discount")
    void fourDifferentBooksShouldReturnOneHundredAndSixty() {


        BookCartDto firstBook = new BookCartDto(FIRST_BOOK_NAME, ONE);
        BookCartDto secondBook = new BookCartDto(SECOND_BOOK_NAME, ONE);
        BookCartDto thirdBook = new BookCartDto(THIRD_BOOK_NAME, ONE);
        BookCartDto fourthBook = new BookCartDto(FOURTH_BOOK_NAME, ONE);


        bookCartDtoList.add(firstBook);
        bookCartDtoList.add(secondBook);
        bookCartDtoList.add(thirdBook);
        bookCartDtoList.add(fourthBook);

        Double actualPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);


        assertEquals(FOUR_DISTINCT_BOOKS_PRICE_WITH_TWENTY_PERCENTAGE_DISCOUNT, actualPrice);
    }

    @Test
    @DisplayName("Five different listOfBooks should get 25% discount")
    void fiveDifferentBooksShouldReturnPriceOneHundredAndEightySeven() {


        BookCartDto firstBook = new BookCartDto(FIRST_BOOK_NAME, ONE);
        BookCartDto secondBook = new BookCartDto(SECOND_BOOK_NAME, ONE);
        BookCartDto thirdBook = new BookCartDto(THIRD_BOOK_NAME, ONE);
        BookCartDto fourthBook = new BookCartDto(FOURTH_BOOK_NAME, ONE);
        BookCartDto fifthBook = new BookCartDto(FIFTH_BOOK_NAME, ONE);


        bookCartDtoList.add(firstBook);
        bookCartDtoList.add(secondBook);
        bookCartDtoList.add(thirdBook);
        bookCartDtoList.add(fourthBook);
        bookCartDtoList.add(fifthBook);

        Double actualPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);


        assertEquals(FIVE_DISTINCT_BOOKS_PRICE_WITH_TWENTYFIVE_PERCENTAGE_DISCOUNT, actualPrice);
    }

    @Test
    @DisplayName("two distinct listOfBooks should only get 5% discount remaining Books with actual price")
    void fivePercentDiscountOnlyForTwoDistinctBooksShouldApply() {


        BookCartDto firstBook = new BookCartDto(FIRST_BOOK_NAME, TWO);
        BookCartDto secondBook = new BookCartDto(SECOND_BOOK_NAME, ONE);

        bookCartDtoList.add(firstBook);
        bookCartDtoList.add(secondBook);


        Double actualPrice = priceSummationServiceImpl.calculateBookPrice(bookCartDtoList);


        assertEquals(TWO_DISTINCT_AND_ONE_SEPARATE_BOOK_WITH_DISCOUNT, actualPrice);
    }

}