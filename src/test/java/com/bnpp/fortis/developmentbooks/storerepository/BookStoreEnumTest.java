package com.bnpp.fortis.developmentbooks.storerepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookStoreEnumTest {
    private static final int TOTAL_NO_OF_DEVELOPMENT_BOOKS = 5;

    @Test
    void bookStoreEnumShouldContainFiveBooks() {

        assertEquals(TOTAL_NO_OF_DEVELOPMENT_BOOKS, BookStoreEnum.values().length);

    }

}