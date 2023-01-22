package com.sber.java13spring.java13springproject.dbexample.dao;

import com.sber.java13spring.java13springproject.dbexample.model.Book;

import java.sql.*;

public class BookDaoJDBCOld {
    //select * from books where id = ?
    public Book findBookById(Integer bookId) {
        try(Connection connection = newConnection()) {
            if (connection != null) {
                System.out.println("Ура! Мы подключились к базе!");
            }
            else {
                System.out.println("Увы! База отдыхает");
            }
            PreparedStatement selectQuery = connection.prepareStatement("select * from books where id = ?");
            selectQuery.setInt(1, bookId); //Если будет ещё знак вопроса, то добавим еще одну строчку с
            //индексом 2 и значением
            
            //теперь обработаем результат в java
            ResultSet resultSet = selectQuery.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setDateAdded(resultSet.getDate("date_added"));
                System.out.println(book);
                return book;
            }
        }
        catch (SQLException e) {
            System.out.println("Error" + e.getSQLState());
        }
        return null;
    }
    
    //Подключение к БД (путь можно найти в Properties)
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/local_db",
                "postgres", "12345");
    }
}