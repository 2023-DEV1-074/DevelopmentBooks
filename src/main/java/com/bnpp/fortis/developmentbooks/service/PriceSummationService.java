package com.bnpp.fortis.developmentbooks.service;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;

import java.util.List;

public interface PriceSummationService {
    public Double calculateBookPrice(List<BookCartDto> bookCartDtoList);
}