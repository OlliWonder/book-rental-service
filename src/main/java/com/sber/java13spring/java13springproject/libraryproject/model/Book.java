package com.sber.java13spring.java13springproject.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "book_seq", allocationSize = 1)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Book extends GenericModel {
    
    @Column(name = "title", nullable = false)
    private String bookTitle;
    
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;
    
    @Column(name = "page_count")
    private Integer pageCount;
    
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @Column(name = "storage_place")
    private String storagePlace;
    
    @Column(name = "online_copy_path")
    private String onlineCopy;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "publish")
    private String publish;
    
    @Column(name = "genre")
    @Enumerated
    private Genre genre;
    /*
    ALL - повторить все операции для дочерних объектов
    PERSIST - повторить сохранение
    MERGE - повторить обновление
    REMOVE - повторить удаление
    DELETE - -//-
    DETACH - повторить удаление из сессии
    orphanRemoval = true - удалить объект, если на него не ссылается ни один родитель
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors",
    joinColumns = @JoinColumn(name = "book_id"), foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS"),
    inverseJoinColumns = @JoinColumn(name = "author_id"), inverseForeignKey = @ForeignKey(name = "FK_AUTHORS_BOOKS"))
    //@JsonBackReference
    private Set<Author> authors;
    
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BookRentInfo> bookRentInfos;
}