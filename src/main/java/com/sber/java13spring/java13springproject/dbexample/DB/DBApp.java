package com.sber.java13spring.java13springproject.dbexample.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.sber.java13spring.java13springproject.dbexample.constants.DBConsts.*;

public enum DBApp { //Класс Енам, как один из вариантов реализации паттерна Синглтон
    INSTANCE;
    
    private Connection connection;
    
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + PORT + "/" + DB, USER, PASSWORD);
    }
}
