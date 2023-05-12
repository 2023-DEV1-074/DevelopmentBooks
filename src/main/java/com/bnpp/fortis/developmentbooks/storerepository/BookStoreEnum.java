package com.bnpp.fortis.developmentbooks.storerepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStoreEnum {


    FIRST_BOOK("Clean Code", "Robert Martin", 50.00),
    SECOND_BOOK("The Clean Coder", "Robert Martin", 50.00),
    THIRD_BOOK("Clean Architecture", "Robert Martin", 50.00),
    FOURTH_BOOK("Test-Driven Development By Example", "Kent Beck", 50.00),
    FIFTH_BOOK("Working Effectively With Legacy Code", "Michael C. Feathers", 50.00);

    private String bookTitle;
    private String author;
    private double price;

}