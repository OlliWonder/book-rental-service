package com.sber.java13spring.java13springproject.libraryproject.MVC.controller;

import com.sber.java13spring.java13springproject.libraryproject.dto.BookDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.BookSearchDTO;
import com.sber.java13spring.java13springproject.libraryproject.dto.BookWithAuthorsDTO;
import com.sber.java13spring.java13springproject.libraryproject.exception.MyDeleteException;
import com.sber.java13spring.java13springproject.libraryproject.service.BookService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Hidden
@Controller
@RequestMapping("/books")
@Slf4j
public class MVCBookController {
    private final BookService bookService;
    
    public MVCBookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    //url: localhost:9090/api/books/?page=1&size=2
    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "bookTitle"));
        Page<BookWithAuthorsDTO> result = bookService.getAllBooksWithAuthors(pageRequest);
        model.addAttribute("books", result);
        return "books/viewAllBooks";
    }
    
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model) {
        model.addAttribute("book", bookService.getBookWithAuthors(id));
        return "books/viewBook";
    }
    
    @GetMapping("/add")
    public String create() {
        return "books/addBook";
    }
    
    @PostMapping("/add")
    public String create(@ModelAttribute("bookForm") BookDTO bookDTO,
                         @RequestParam MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            bookService.create(bookDTO, file);
        }
        else {
            bookService.create(bookDTO);
        }
        return "redirect:/books";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        model.addAttribute("book", bookService.getOne(id));
        return "books/updateBook";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute("bookForm") BookDTO bookDTO,
                         @RequestParam MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            bookService.update(bookDTO, file);
        }
        else {
            bookService.update(bookDTO);
        }
        return "redirect:/books";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            bookService.delete(id);
        }
        catch (MyDeleteException e) {
            log.error("MVCBookController#delete(): {}", e.getMessage());
            return "redirect:/error/error-message?message=" + e.getLocalizedMessage();
        }
        return "redirect:/books";
    }
    
    @PostMapping("/search")
    public String searchBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("bookSearchForm") BookSearchDTO bookSearchDTO,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        model.addAttribute("books", bookService.findBooks(bookSearchDTO, pageRequest));
        return "books/viewAllBooks";
    }
    
    @GetMapping(value = "/download", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadBook(@Param(value = "bookId") Long bookId) throws IOException {
        BookDTO bookDTO = bookService.getOne(bookId);
        Path path = Paths.get(bookDTO.getOnlineCopyPath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        
        return ResponseEntity.ok()
                .headers(this.headers(path.getFileName().toString()))
                .contentLength(path.toFile().length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    private HttpHeaders headers(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }
}
