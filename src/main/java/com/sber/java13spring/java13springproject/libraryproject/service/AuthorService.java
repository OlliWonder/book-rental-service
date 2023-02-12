package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.AuthorDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.AuthorMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.Author;
import com.sber.java13spring.java13springproject.libraryproject.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends GenericService<Author, AuthorDTO> {
    protected AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        super(authorRepository, authorMapper);
    }
}
