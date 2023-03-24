package com.sber.java13spring.java13springproject.libraryproject.constants;

import java.util.List;

public interface SecurityConstants {
    
    List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/js/**",
            "/css/**",
            "/",
            // -- Swagger UI v3 (OpenAPI)
            "/swagger-ui/**",
            "/webjars/bootstrap/5.0.2/**",
            "/v3/api-docs/**");
    
    List<String> BOOKS_WHITE_LIST = List.of("/books",
            "/books/search",
            "/books/{id}");
    List<String> BOOKS_PERMISSION_LIST = List.of("/books/add",
            "/books/update",
            "/books/delete",
            "/books/download/{bookId}");
    
    List<String> USERS_WHITE_LIST = List.of("/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");
    
    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}
