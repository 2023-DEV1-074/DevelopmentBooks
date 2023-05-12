package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.model.BookMapper;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevelopmentBooksServiceImpl implements DevelopmentBooksService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return Arrays.stream(BookStoreEnum.values()).map(bookStackEnum -> bookMapper.mapper(bookStackEnum))
                .collect(Collectors.toList());
    }
}