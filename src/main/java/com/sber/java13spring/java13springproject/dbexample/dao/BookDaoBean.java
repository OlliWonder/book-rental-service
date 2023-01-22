package com.sber.java13spring.java13springproject.dbexample.dao;

import com.sber.java13spring.java13springproject.dbexample.DB.DBApp;
import com.sber.java13spring.java13springproject.dbexample.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class BookDaoBean {
    private final Connection connection;
    public BookDaoBean(Connection connection) {
        this.connection = connection;
    }
    
    public Book findBookById(Integer bookId) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement("select * from books where id = ?");
        selectQuery.setInt(1, bookId); //Если будет ещё знак вопроса, то добавим еще одну строчку с
        //индексом 2 и значением
    
        //теперь обработаем результат в java
        ResultSet resultSet = selectQuery.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setBookTitle(resultSet.getString("title"));
            book.setBookAuthor(resultSet.getString("author"));
            book.setDateAdded(resultSet.getDate("date_added"));
            System.out.println(book);
        }
        return book;
    }
}
