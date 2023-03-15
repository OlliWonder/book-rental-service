package com.sber.java13spring.java13springproject.libraryproject.mapper;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookRentInfoDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.BookRentInfo;
import com.sber.java13spring.java13springproject.libraryproject.repository.BookRepository;
import com.sber.java13spring.java13springproject.libraryproject.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Set;

@Component
public class BookRentInfoMapper extends GenericMapper<BookRentInfo, BookRentInfoDTO> {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    
    protected BookRentInfoMapper(ModelMapper mapper,
                                 BookRepository bookRepository,
                                 UserRepository userRepository) {
        super(mapper, BookRentInfo.class, BookRentInfoDTO.class);
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    
    @PostConstruct
    @Override
    public void setupMapper() {
        super.modelMapper.createTypeMap(BookRentInfo.class, BookRentInfoDTO.class)
              .addMappings(m -> m.skip(BookRentInfoDTO::setUserId)).setPostConverter(toDtoConverter())
              .addMappings(m -> m.skip(BookRentInfoDTO::setBookId)).setPostConverter(toDtoConverter());
    }
    
    @Override
    protected void mapSpecificFields(BookRentInfoDTO source, BookRentInfo destination) {
        destination.setBook(bookRepository.findById(source.getBookId()).orElseThrow(() -> new NotFoundException("Книги не найдено")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
    }
    
    @Override
    protected void mapSpecificFields(BookRentInfo source, BookRentInfoDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setBookId(source.getBook().getId());
    }
    
    @Override
    protected Set<Long> getIds(BookRentInfo entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
