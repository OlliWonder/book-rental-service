package com.sber.java13spring.java13springproject.libraryproject.dto;

import com.sber.java13spring.java13springproject.libraryproject.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO extends GenericDTO {
    
    private String bookTitle;
    private String publishDate;
    private Integer pageCount;
    private Integer amount;
    private String storagePlace;
    private String onlineCopy;
    private String description;
    private String publish;
    private Genre genre;
    private Set<Long> authorsIds;
}
