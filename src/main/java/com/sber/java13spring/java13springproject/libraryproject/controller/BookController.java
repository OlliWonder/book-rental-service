package com.sber.java13spring.java13springproject.libraryproject.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import com.sber.java13spring.java13springproject.libraryproject.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Книги", description = "Контроллер для работы с книгами библиотеки")
//localhost:8080/api/rest/books
public class BookController extends GenericController<Book, BookDTO> {
    
    public BookController(BookService bookService) {
        super(bookService);
    }
}
