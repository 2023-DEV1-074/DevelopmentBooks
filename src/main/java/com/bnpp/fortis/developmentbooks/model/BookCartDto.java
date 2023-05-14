package com.bnpp.fortis.developmentbooks.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookCartDto {
    @ApiModelProperty(notes = "Name of the Book", name = "name", required = true, value = "Clean Code")
    private String name;

    @ApiModelProperty(notes = "Quantity of the Book", name = "quantity", required = true, value = "2")
    private int quantity;

}
