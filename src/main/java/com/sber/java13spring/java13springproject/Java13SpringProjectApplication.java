package com.sber.java13spring.java13springproject;

import com.sber.java13spring.java13springproject.dbexample.MyDBConfigContext;
import com.sber.java13spring.java13springproject.dbexample.dao.BookDaoBean;
import com.sber.java13spring.java13springproject.dbexample.dao.BookDaoJDBC;
import com.sber.java13spring.java13springproject.dbexample.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Java13SpringProjectApplication implements CommandLineRunner {
    private BookDaoBean bookDaoBean;
    
//    @Autowired //Сюда произойдёт инжект нашего бина БукДаоБин, который помечен аннотацией компонент
//    public void setBookDaoBean(BookDaoBean bookDaoBean) {
//        this.bookDaoBean = bookDaoBean;
//    }
    
    //Ещё способо автовайринга - injection через конструктор нашего основного класса (даже автовайринг писать не надо)
    public Java13SpringProjectApplication(BookDaoBean bookDaoBean) {
        this.bookDaoBean = bookDaoBean;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Java13SpringProjectApplication.class, args);
        
    }
    
    @Override
    public void run(String... args) throws Exception {
        BookDaoJDBC bookDaoJDBC = new BookDaoJDBC();
        bookDaoJDBC.findBookById(1);
        
    // Вариант работы с помощью своего собственного контекста:
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyDBConfigContext.class);
//        BookDaoBean bookDaoBean = ctx.getBean(BookDaoBean.class);
//        bookDaoBean.findBookById(1);
        
        bookDaoBean.findBookById(1);
    }
}
