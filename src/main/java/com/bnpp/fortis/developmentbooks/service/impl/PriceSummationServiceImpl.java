package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import com.bnpp.fortis.developmentbooks.service.PriceSummationService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceSummationServiceImpl implements PriceSummationService {
    @Override
    public Double calculateBookPrice(List<BookCartDto> bookCartDtoList) {
        Map<String, Double> bookTitlePriceMap = Arrays.stream(BookStoreEnum.values())
                .collect(Collectors.toMap(BookStoreEnum::getBookTitle, BookStoreEnum::getPrice));
        return bookCartDtoList.stream().mapToDouble(bookCartDto -> bookTitlePriceMap.get(bookCartDto.getName()) * bookCartDto.getQuantity()).sum();
    }
}