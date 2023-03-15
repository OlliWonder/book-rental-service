package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.RoleDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.UserDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.UserMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.User;
import com.sber.java13spring.java13springproject.libraryproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService extends GenericService<User, UserDTO> {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    protected UserService(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }
    
    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }
    
    @Override
    public UserDTO create(UserDTO object) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        object.setRole(roleDTO);
        object.setCreatedBy("REGISTRATION FORM");
        object.setCreatedWhen(LocalDateTime.now());
        object.setPassword(bCryptPasswordEncoder.encode(object.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
}
