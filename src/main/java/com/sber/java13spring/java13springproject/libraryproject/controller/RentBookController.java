package com.sber.java13spring.java13springproject.libraryproject.controller;

import com.sber.java13spring.java13springproject.libraryproject.model.BookRentInfo;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRentInfoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Аренда книг",
        description = "Контроллер для работы с арендой/выдачей книг пользователям библиотеки")
public class RentBookController
        extends GenericController<BookRentInfo> {
    
    private final BookRentInfoRepository repository;
    
    public RentBookController(BookRentInfoRepository repository) {
        super(repository);
        this.repository = repository;
    }
}