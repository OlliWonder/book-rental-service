package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookRentInfoDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.BookRentInfoMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.BookRentInfo;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRentInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRentInfoService extends GenericService<BookRentInfo, BookRentInfoDTO> {
    
    protected BookRentInfoService(BookRentInfoRepository bookRentInfoRepository,
                                  BookRentInfoMapper bookRentInfoMapper) {
        super(bookRentInfoRepository, bookRentInfoMapper);
    }
}
