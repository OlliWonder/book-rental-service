package com.sber.java13spring.java13springproject.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRentInfoDTO extends GenericDTO {
    
    private BookDTO book;
    private UserDTO user;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private Boolean returned;
    private Integer rentPeriod;
    private Long bookId;
    private Long userId;
    
}
