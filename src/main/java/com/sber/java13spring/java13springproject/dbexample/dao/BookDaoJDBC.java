package com.sber.java13spring.java13springproject.dbexample.dao;

import com.sber.java13spring.java13springproject.dbexample.DB.DBApp;
import com.sber.java13spring.java13springproject.dbexample.model.FirstBook;

import java.sql.*;
//В классе букДАО мы храним все методы для работы с книгами в базе данных
public class BookDaoJDBC { //В этой версии ДАО мы вынесли путь в е-нам класс
    //select * from books where id = ?
    public FirstBook findBookById(Integer bookId) {
        try(Connection connection = DBApp.INSTANCE.newConnection()) { //INSTANCE - пример синглтона, через инстанс будем
            // получать единственный коннекшн/в единственном экземпляре
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
                FirstBook book = new FirstBook();
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
    
    //Пример метода для поиска списка книг по названию
    //puplic List<Books> findBooksByTitle(String title) {
    //}
    
    //Подключение к БД (путь можно найти в Properties)
//    public Connection newConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/local_db",
//                "postgres", "12345");
//    }
}
