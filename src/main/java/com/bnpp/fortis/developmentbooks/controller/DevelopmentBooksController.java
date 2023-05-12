package com.bnpp.fortis.developmentbooks.controller;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(value = "Get list of Books in the Store ", response = Iterable.class, tags = "getAllBooks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping("${developmentbooks.endpoints.getallbooks}")
    public List<Book> getallbooks() {
        return developmentBooksService.getAllBooks();
    }

}