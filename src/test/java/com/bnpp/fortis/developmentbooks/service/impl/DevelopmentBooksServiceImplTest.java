package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DevelopmentBooksServiceImplTest {
    private static final int TOTAL_NUMBER_OF_BOOKS = 5;

    @Autowired
    private DevelopmentBooksServiceImpl developmentBooksService;

    @Test
    @DisplayName("Get All Books should return five books")
    void getAllBooksShouldReturnAllFiveBooks() {

        List<Book> books = developmentBooksService.getAllBooks();

        assertEquals(TOTAL_NUMBER_OF_BOOKS, books.size());
    }


}