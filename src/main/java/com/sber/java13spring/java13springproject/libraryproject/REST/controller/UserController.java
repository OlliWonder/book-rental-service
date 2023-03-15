package com.sber.java13spring.java13springproject.libraryproject.REST.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.UserDTO;
import com.sber.java13spring.java13springproject.libraryproject.model.User;
import com.sber.java13spring.java13springproject.libraryproject.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями библиотеки")
public class UserController extends GenericController<User, UserDTO> {
    
    public UserController(UserService userService) {
        super(userService);
    }
}