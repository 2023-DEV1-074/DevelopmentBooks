package com.bnpp.fortis.developmentbooks.controller;

import com.bnpp.fortis.developmentbooks.service.DevelopmentBooksService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DevelopmentBooksController.class)
class DevelopmentBooksControllerTest {

    private static final String GET_ALL_BOOKS_URL = "/api/developmentbooks/getallbooks";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DevelopmentBooksService developmentBooksService;

    @Autowired
    private DevelopmentBooksController developmentBooksController;

    @Test
    @DisplayName("development book controller instance should not be null")
    void developmentControllershouldNotBeNull() {
        Assertions.assertThat(developmentBooksController).isNotNull();
    }

    @Test
    @DisplayName(" API getBooks should return status OK")
    void getBooksApiShouldReturnStatusOK() throws Exception {
        mockMvc.perform(get(GET_ALL_BOOKS_URL))
                .andExpect(status().isOk());

    }

}