package com.bnpp.fortis.developmentbooks.service;

import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import com.bnpp.fortis.developmentbooks.model.CartSummaryReportDto;

import java.util.List;

public interface PriceSummationService {
    public CartSummaryReportDto calculateBookPrice(List<BookCartDto> bookCartDtoList);
}