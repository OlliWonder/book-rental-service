package com.sber.java13spring.java13springproject.libraryproject.REST.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.AuthorDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.Author;
import com.sber.java13spring.java13springproject.libraryproject.service.AuthorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/authors")
@Tag(name = "Авторы", description = "Контроллер для работы с авторами книг библиотеки")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorController extends GenericController<Author, AuthorDTO> {
    private AuthorService authorService;
    
    public AuthorController(AuthorService authorService) {
        super(authorService);
        this.authorService = authorService;
    }

//    @Secured(value = "ROLE_ADMIN")
//    public void test() {
//
//    }
}