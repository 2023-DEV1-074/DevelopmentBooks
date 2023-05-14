package com.bnpp.fortis.developmentbooks.exception;
import com.bnpp.fortis.developmentbooks.model.BookApiExceptionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

    private static final String INVALID_BOOK_ERROR_CODE = "BOOK_00000";
    private static final String INVALID_BOOK_QUANTITY_ERROR_CODE = "BOOK_00001";


    @ExceptionHandler({InvalidBookException.class})
    public ResponseEntity<Object> handleBookTitleBadRequestException(InvalidBookException ex) {
        BookApiExceptionMessage bookApiException = new BookApiExceptionMessage(INVALID_BOOK_ERROR_CODE, ex.getMessage());
        return new ResponseEntity<Object>(bookApiException, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidQuantityException.class})
    public ResponseEntity<Object> handleBookQuantityBadRequestException(InvalidQuantityException ex) {
        BookApiExceptionMessage bookApiException = new BookApiExceptionMessage(INVALID_BOOK_QUANTITY_ERROR_CODE, ex.getMessage());
        return new ResponseEntity<Object>(bookApiException, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
