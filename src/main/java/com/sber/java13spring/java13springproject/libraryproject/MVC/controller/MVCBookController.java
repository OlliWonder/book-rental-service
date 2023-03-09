package com.sber.java13spring.java13springproject.libraryproject.MVC.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.BookWithAuthorsDTO;
import com.sber.java13spring.java13springproject.libraryproject.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("books")
public class MVCBookController {
    private final BookService bookService;
    
    public MVCBookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("")
    public String getAll(Model model) {
        List<BookWithAuthorsDTO> bookDTOList = bookService.getAllBooksWithAuthors();
        model.addAttribute("books", bookDTOList);
        return "books/viewAllBooks";
    }
    
    //Отобразит форму для создания книги
    @GetMapping("/add")
    public String create() {
        return "books/addBook";
    }
    
    //Примет данные о создаваемой книге и создаст в БД
    //Потом вернет нас на страницу со всеми книгами
    @PostMapping("/add")
    public String create(@ModelAttribute("bookForm") BookDTO bookDTO) {
        bookService.create(bookDTO);
        return "redirect:/books";
    }
}
