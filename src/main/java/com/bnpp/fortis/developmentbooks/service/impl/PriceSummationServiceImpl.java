package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import com.bnpp.fortis.developmentbooks.service.PriceSummationService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import com.bnpp.fortis.developmentbooks.storerepository.DiscountDetailsEnum;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceSummationServiceImpl implements PriceSummationService {

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int ZERO_PERCENT = 0;

    private static final int ONE_QUANTITY = 1;

    private static final int FIVE = 5;
    private static final int TEN = 10;

    private static final int HUNDRED = 100;

    @Override
    public Double calculateBookPrice(List<BookCartDto> bookCartDtoList) {
        Map<String, Double> bookTitlePriceMap = Arrays.stream(BookStoreEnum.values())
                .collect(Collectors.toMap(BookStoreEnum::getBookTitle, BookStoreEnum::getPrice));
        long distinctBooks = bookCartDtoList.stream().map(BookCartDto::getName).distinct().count();
        Map<String, Integer> listOfbookWithQuantityMap = bookCartDtoList.stream()
                .collect(Collectors.toMap(BookCartDto::getName, BookCartDto::getQuantity));
        int distinctBooksCount = listOfbookWithQuantityMap.size();
        List<String> distinctListOfBooks = listOfbookWithQuantityMap.keySet().stream().limit(distinctBooksCount)
                .collect(Collectors.toList());
        double actualPriceforDistinctBooks = distinctListOfBooks.stream()
                .mapToDouble(bookName -> bookTitlePriceMap.get(bookName)).sum();
        double discountedPrice = (actualPriceforDistinctBooks * getDiscountPercentage(distinctBooksCount)) / HUNDRED;
        distinctListOfBooks.forEach(bookName -> {
            int quantity = listOfbookWithQuantityMap.get(bookName);
            if (quantity > ONE_QUANTITY) {
                listOfbookWithQuantityMap.put(bookName, quantity - ONE_QUANTITY);
            } else {
                listOfbookWithQuantityMap.remove(bookName);
            }
        });
        double priceForDiscountedBooks = actualPriceforDistinctBooks - discountedPrice;
        Set<String> remainingBooks = listOfbookWithQuantityMap.keySet();
        double priceForRemainingBooks = remainingBooks.stream()
                .mapToDouble(bookName -> listOfbookWithQuantityMap.get(bookName) * bookTitlePriceMap.get(bookName)).sum();
        return (priceForDiscountedBooks + priceForRemainingBooks);
    }

    private int getDiscountPercentage(long numberOfDistinctBooks) {

        Optional<DiscountDetailsEnum> checkDiscount = Arrays.stream(DiscountDetailsEnum.values()).filter(discountGroup -> discountGroup.getNumberOfDistinctItems() == numberOfDistinctBooks).findFirst();

        return (checkDiscount.isPresent()) ? checkDiscount.get().getDiscountPercentage() : ZERO_PERCENT;
    }
}