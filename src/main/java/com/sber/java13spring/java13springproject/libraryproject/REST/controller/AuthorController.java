package com.sber.java13spring.java13springproject.libraryproject.REST.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.AuthorDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.Author;
import com.sber.java13spring.java13springproject.libraryproject.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@Tag(name = "Авторы", description = "Контроллер для работы с авторами книг библиотеки")
public class AuthorController extends GenericController<Author, AuthorDTO> {
    
    public AuthorController(AuthorService authorService) {
        super(authorService);
    }
}