package com.sber.java13spring.java13springproject.libraryproject.constants;

public interface Errors {
    class Books {
        public static final String BOOK_DELETE_ERROR = "Книга не может быть удалена, так как у неё есть активные аренды";
    }
    
    class Authors{
        public static final String AUTHOR_DELETE_ERROR = "Автор не может быть удален, так как у его книг есть активные аренды";
    }
    
    class Users{
        public static final String USER_FORBIDDEN_ERROR = "У вас нет прав просматривать информацию о пользователе";
    }
}
