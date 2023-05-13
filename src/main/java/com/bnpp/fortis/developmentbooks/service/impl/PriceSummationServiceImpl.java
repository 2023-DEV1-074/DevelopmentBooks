package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import com.bnpp.fortis.developmentbooks.service.PriceSummationService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import com.bnpp.fortis.developmentbooks.storerepository.DiscountDetailsEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceSummationServiceImpl implements PriceSummationService {

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int ZERO_PERCENT = 0;


    private static final int FIVE = 5;
    private static final int TEN = 10;

    private static final int HUNDRED = 100;

    @Override
    public Double calculateBookPrice(List<BookCartDto> bookCartDtoList) {
        Map<String, Double> bookTitlePriceMap = Arrays.stream(BookStoreEnum.values())
                .collect(Collectors.toMap(BookStoreEnum::getBookTitle, BookStoreEnum::getPrice));
        long distinctBooks = bookCartDtoList.stream().map(BookCartDto::getName).distinct().count();

        double actualPrice = bookCartDtoList.stream()
                .mapToDouble(book -> bookTitlePriceMap.get(book.getName()) * book.getQuantity()).sum();
        double discountedPrice = (actualPrice * getDiscountPercentage(distinctBooks)) / HUNDRED;

        return (actualPrice - discountedPrice);
    }

    private int getDiscountPercentage(long numberOfDistinctBooks) {
        
        Optional<DiscountDetailsEnum> checkDiscount = Arrays.stream(DiscountDetailsEnum.values()).filter(discountGroup -> discountGroup.getNumberOfDistinctItems() == numberOfDistinctBooks).findFirst();

        return (checkDiscount.isPresent()) ? checkDiscount.get().getDiscountPercentage() : ZERO_PERCENT;
    }
}