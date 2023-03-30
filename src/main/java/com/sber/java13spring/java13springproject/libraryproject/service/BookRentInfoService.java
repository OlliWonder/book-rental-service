package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.BookRentInfoDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.BookRentInfoMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.BookRentInfo;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRentInfoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookRentInfoService extends GenericService<BookRentInfo, BookRentInfoDTO> {
    private final BookService bookService;
    private final BookRentInfoMapper bookRentInfoMapper;
    
    protected BookRentInfoService(BookRentInfoRepository bookRentInfoRepository,
                                  BookRentInfoMapper bookRentInfoMapper,
                                  BookService bookService) {
        super(bookRentInfoRepository, bookRentInfoMapper);
        this.bookService = bookService;
        this.bookRentInfoMapper = bookRentInfoMapper;
    }
    
    public BookRentInfoDTO rentBook(BookRentInfoDTO rentBookDTO) {
        BookDTO bookDTO = bookService.getOne(rentBookDTO.getBookId());
        bookDTO.setAmount(bookDTO.getAmount() - 1);
        bookService.update(bookDTO);
        long rentPeriod = rentBookDTO.getRentPeriod() != null ? rentBookDTO.getRentPeriod() : 14L;
        rentBookDTO.setRentDate(LocalDateTime.now());
        rentBookDTO.setReturned(false);
        rentBookDTO.setRentPeriod((int) rentPeriod);
        rentBookDTO.setReturnDate(LocalDateTime.now().plusDays(rentPeriod));
        rentBookDTO.setCreatedWhen(LocalDateTime.now());
        rentBookDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDTO(repository.save(mapper.toEntity(rentBookDTO)));
    }
    
    public void returnBook(final Long id) {
        BookRentInfoDTO bookRentInfoDTO = getOne(id);
        bookRentInfoDTO.setReturned(true);
        bookRentInfoDTO.setReturnDate(LocalDateTime.now());
        BookDTO bookDTO = bookRentInfoDTO.getBookDTO();
        bookDTO.setAmount(bookDTO.getAmount() + 1);
        update(bookRentInfoDTO);
        bookService.update(bookDTO);
    }
}
