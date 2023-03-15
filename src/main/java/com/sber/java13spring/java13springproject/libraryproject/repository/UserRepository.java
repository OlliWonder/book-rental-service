package com.sber.java13spring.java13springproject.libraryproject.repository;

import com.sber.java13spring.java13springproject.libraryproject.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {
    
    // Свой запрос с помощью аннотации:
    // @Query(nativeQuery = true, value = "select * from users where login = :login");
    User findUserByLogin(String login); //select * from users where login = ?
    
    User findUserByEmail(String email);
}