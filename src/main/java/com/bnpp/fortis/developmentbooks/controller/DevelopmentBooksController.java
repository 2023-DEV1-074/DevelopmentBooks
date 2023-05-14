package com.bnpp.fortis.developmentbooks.controller;

import com.bnpp.fortis.developmentbooks.model.Book;
import com.bnpp.fortis.developmentbooks.model.BooksData;
import com.bnpp.fortis.developmentbooks.model.CartSummaryReportDto;
import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import com.bnpp.fortis.developmentbooks.service.PriceSummationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${developmentbooks.controller.path}")
@RequiredArgsConstructor
public class DevelopmentBooksController {

    Logger logger = LoggerFactory.getLogger(DevelopmentBooksController.class);


    private final DevelopmentBooksService developmentBooksService;

    private final PriceSummationService priceSummationService;





    @ApiOperation(value = "Get list of Books in the Store ", response = Iterable.class, tags = "getAllBooks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping("${developmentbooks.endpoints.getallbooks}")
    public List<Book> getallbooks() {
        logger.debug("DevelopmentBooksController : getallbooks:  getallbooks api request ");

        List<Book> listOfBooks = developmentBooksService.getAllBooks();

        logger.debug("DevelopmentBooksController : getallbooks:  getallbooks api response: " + listOfBooks);

        return listOfBooks;
    }


    @ApiOperation(value = "API Produces Calculated Book Summary Report With Best Price Of Discounts ", response = CartSummaryReportDto.class, tags = "calculateDiscountPrice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 400, message = "Bad Request, Given Input is not Matching the Store Books!"),
            @ApiResponse(code = 404, message = "not found!!!")
    })
    @PostMapping("${developmentbooks.endpoints.calculatediscountprice}")
    public CartSummaryReportDto calculateDiscountPrice(@RequestBody BooksData booksData) {
        logger.debug("DevelopmentBooksController : calculateDiscountPrice: calculateDiscountPrice api request " + booksData);

        CartSummaryReportDto cartSummaryReportDto = priceSummationService.getCartSummaryReport(booksData.getBookList());

        logger.debug("DevelopmentBooksController : calculateDiscountPrice: calculateDiscountPrice api response " + cartSummaryReportDto);

        return cartSummaryReportDto;
    }
}