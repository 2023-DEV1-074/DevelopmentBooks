package com.bnpp.fortis.developmentbooks.exception;

import java.util.List;

public class InvalidBookException extends RuntimeException {

    public InvalidBookException(List<String> invalidBooks) {
        super("Books which are given is not available in Book Store: " + invalidBooks);
    }
}
