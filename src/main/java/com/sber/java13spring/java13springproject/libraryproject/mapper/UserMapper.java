package com.sber.java13spring.java13springproject.libraryproject.mapper;

import com.sber.java13spring.java13springproject.libraryproject.dto.UserDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {
    protected UserMapper(ModelMapper modelMapper) {
        super(modelMapper, User.class, UserDTO.class);
    }
    
    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
    
    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
    
    @Override
    protected Set<Long> getIds(User entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
