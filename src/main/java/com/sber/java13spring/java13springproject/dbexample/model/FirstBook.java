package com.sber.java13spring.java13springproject.dbexample.model;

import lombok.*;

import java.util.Date;
// POJO - Plain Old Java Object (описательный класс, имеет конструкторы и методы для получения полей,
// без бизнес-логики)

@Data //Автоматически делает геттеры и сеттеры для всех полей и переопр equals и hashCode. Есть ешё @Getter и @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FirstBook {
    @Setter(AccessLevel.NONE)
    //@Getter(AccessLevel.NONE)
    private Integer bookId;
    private String bookTitle;
    private String bookAuthor;
    private Date dateAdded;
}
