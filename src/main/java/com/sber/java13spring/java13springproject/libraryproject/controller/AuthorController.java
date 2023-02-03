package com.sber.java13spring.java13springproject.libraryproject.controller;

import com.sber.java13spring.java13springproject.libraryproject.model.Author;
import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import com.sber.java13spring.java13springproject.libraryproject.repository.AuthorRepository;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/authors")
@Tag(name = "Авторы", description = "Контроллер для работы с авторами книг библиотеки")
public class AuthorController extends GenericController<Author> {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    
    public AuthorController(BookRepository bookRepository, AuthorRepository authorRepository) {
        super(authorRepository);
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    @Operation(description = "Добавить книгу к автору", method = "addBook")
    @RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> addAuthor(@RequestParam(value = "bookId") Long bookId,
                                            @RequestParam(value = "authorId") Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга с переданным ID не найдена"));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Автор с таким ID не найден"));
        author.getBooks().add(book);
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.save(author));
    }
}