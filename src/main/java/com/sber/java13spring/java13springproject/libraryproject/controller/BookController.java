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
@RequestMapping("/books")
@Tag(name = "Книги", description = "Контроллер для работы с книгами библиотеки")
//localhost:8080/api/rest/books
public class BookController extends GenericController<Book> {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    @Operation(description = "Добавить автора к книге", method = "addAuthor")
    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addAuthor(@RequestParam(value = "bookId") Long bookId,
                                          @RequestParam(value = "authorId") Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга с переданным ID не найдена"));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Автор с таким ID не найден"));
        book.getAuthors().add(author);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
    }
}
