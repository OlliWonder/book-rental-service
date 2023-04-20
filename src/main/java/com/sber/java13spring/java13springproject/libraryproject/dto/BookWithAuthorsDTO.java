package com.sber.java13spring.java13springproject.libraryproject.dto;

import com.sber.java13spring.java13springproject.libraryproject.model.Book;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookWithAuthorsDTO extends BookDTO {
    private Set<AuthorDTO> authors;
    
    public BookWithAuthorsDTO(Book book, Set<AuthorDTO> authors) {
        super(book);
        this.authors = authors;
    }
}
