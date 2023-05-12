package com.bnpp.fortis.developmentbooks.controller;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${developmentbooks.controller.path}")
@RequiredArgsConstructor
public class DevelopmentBooksController {

    private final DevelopmentBooksService developmentBooksService;

    @GetMapping("${developmentbooks.endpoints.getallbooks}")
    public List<Book> getallbooks() {
        return developmentBooksService.getAllBooks();
    }

}