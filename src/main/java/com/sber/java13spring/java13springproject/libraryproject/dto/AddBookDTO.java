package com.sber.java13spring.java13springproject.libraryproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBookDTO {
    Long bookId;
    Long authorId;
}
