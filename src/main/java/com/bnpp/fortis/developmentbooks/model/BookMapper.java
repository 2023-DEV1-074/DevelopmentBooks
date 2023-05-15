package com.bnpp.fortis.developmentbooks.model;

import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book mapper(BookStoreEnum bookStoreEnum);

}