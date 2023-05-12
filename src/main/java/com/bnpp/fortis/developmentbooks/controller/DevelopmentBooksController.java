package com.bnpp.fortis.developmentbooks.controller;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/developmentbooks")
public class DevelopmentBooksController {

    @Autowired
    private DevelopmentBooksService developmentBooksService;

    @GetMapping("/getallbooks")
    public List<Book> getallbooks() {
        return developmentBooksService.getAllBooks();
    }
}