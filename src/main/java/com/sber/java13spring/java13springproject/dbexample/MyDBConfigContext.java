package com.sber.java13spring.java13springproject.dbexample;

import com.sber.java13spring.java13springproject.dbexample.dao.BookDaoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.sber.java13spring.java13springproject.dbexample.constants.DBConsts.*;
//Создаём свой собственный конфигурационный контекст с помощью аннотации
@Configuration //делает класс зависимым глобально
//@ComponentScan //заменяем создание нового ДаоБин, аннотация смотрит на все классы java в том пакете, где мы находимся
//и на все подпакеты и если они выглядят как конфигурация Спринга, то оно забирает это к себе в контекст
//ИЛИ же используем @Autowired в мэйн-классе
public class MyDBConfigContext {
    @Bean //думаем о методах бина, как о фабричных методах (этот паттерн дает различную реализацию каки-то объектов)
    //Экземпляры, созданные с помощью бина, наз-ся бин-компонентами. Спринг-контейнер создал их, и они находятся под его
    //управлением.
    @Scope("singleton") //по умолчанию всегда синглтон (сколько источников данных?)
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + PORT + "/" + DB, USER, PASSWORD);
    }
    
//    @Bean
//    public BookDaoBean bookDaoBean() throws SQLException { //Сложно, не надо так
//        return new BookDaoBean(newConnection());
//    }
}
