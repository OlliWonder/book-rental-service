package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.BookMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends GenericService<Book, BookDTO> {
    //Инжектим конкретный репозиторий для работы с таблицей books
    //private final BookRepository repository;
    
    protected BookService(BookRepository repository, BookMapper mapper) {
        //Передаем этот репозиторий в абстрактный сервис,
        //чтобы он понимал с какой таблицей будут выполняться CRUD операции
        super(repository, mapper);
    }
}
