package com.bnpp.fortis.developmentbooks.service;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;

public interface PriceSummationService {
    public Double calculateBookPrice(BookCartDto bookCartDto);
}