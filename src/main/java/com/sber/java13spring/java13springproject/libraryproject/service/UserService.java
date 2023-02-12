package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.UserDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.UserMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.User;
import com.sber.java13spring.java13springproject.libraryproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserDTO> {
    
    protected UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }
}
