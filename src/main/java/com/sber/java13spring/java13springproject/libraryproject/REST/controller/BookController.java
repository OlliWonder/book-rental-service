package com.sber.java13spring.java13springproject.libraryproject.REST.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import com.sber.java13spring.java13springproject.libraryproject.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/books")
@Tag(name = "Книги", description = "Контроллер для работы с книгами библиотеки")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
//localhost:8080/api/rest/books
public class BookController extends GenericController<Book, BookDTO> {
    
    public BookController(BookService bookService) {
        super(bookService);
    }
}
