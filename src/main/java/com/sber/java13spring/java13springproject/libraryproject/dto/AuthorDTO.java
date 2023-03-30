package com.sber.java13spring.java13springproject.libraryproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorDTO extends GenericDTO {
    private String authorFio;
    private String birthDate;
    private String description;
    private Set<Long> booksIds;
    private boolean isDeleted;
}
