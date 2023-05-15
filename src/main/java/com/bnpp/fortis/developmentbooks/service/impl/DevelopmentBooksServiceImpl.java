package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.model.BookMapper;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevelopmentBooksServiceImpl implements DevelopmentBooksService {

    private final BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return Arrays.stream(BookStoreEnum.values()).map(bookStackEnum -> bookMapper.mapper(bookStackEnum))
                .collect(Collectors.toList());
    }
}