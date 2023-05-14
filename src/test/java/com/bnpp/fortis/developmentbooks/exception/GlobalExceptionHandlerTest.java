package com.bnpp.fortis.developmentbooks.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    private static final String INVALID_BOOK = "invalid book";

    private static final int INVALID_QUANTITY = -1;

    @Test
    void handleBookTitleBadRequestException() {
        List<String> invalidBooks = new ArrayList<>();
        invalidBooks.add(INVALID_BOOK);
        InvalidBookException exception = new InvalidBookException(invalidBooks);
        ResponseEntity<Object> result = handler.handleBookTitleBadRequestException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void handleBookQuantityBadRequestException() {
        List<Integer> invalidQuantity = new ArrayList<>();
        invalidQuantity.add(INVALID_QUANTITY);
        InvalidQuantityException exception = new InvalidQuantityException(invalidQuantity);
        ResponseEntity<Object> result = handler.handleBookQuantityBadRequestException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    @Test
    void handleBadRequestJsonMappingException() {

        ResponseEntity<Object> result = handler.handleJsonMappingException(new JsonMappingException("invalid json"));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }


}