package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.BookWithAuthorsDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.BookMapper;
import com.sber.java13spring.java13springproject.libraryproject.mapper.BookWithAuthorsMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends GenericService<Book, BookDTO> {
    //Инжектим конкретный репозиторий для работы с таблицей books
    //private final BookRepository repository;
    
    private final BookRepository bookRepository;
    private final BookWithAuthorsMapper bookWithAuthorsMapper;
    
    protected BookService(BookRepository bookRepository, BookMapper bookMapper,
                          BookWithAuthorsMapper bookWithAuthorsMapper) {
        //Передаем этот репозиторий в абстрактный сервис,
        //чтобы он понимал с какой таблицей будут выполняться CRUD операции
        super(bookRepository, bookMapper);
        this.bookRepository = bookRepository;
        this.bookWithAuthorsMapper = bookWithAuthorsMapper;
    }
    
    public List<BookWithAuthorsDTO> getAllBooksWithAuthors() {
        return bookWithAuthorsMapper.toDTOs(bookRepository.findAll());
    }
}
