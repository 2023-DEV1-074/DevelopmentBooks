package com.bnpp.fortis.developmentbooks.exception;

import java.util.List;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(List<Integer> invalidQuantities) {
        super("Quantity which are given is not valid , Quantity should be > 0 : " + invalidQuantities);
    }
}
