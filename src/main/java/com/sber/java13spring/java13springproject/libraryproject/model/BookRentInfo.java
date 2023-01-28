package com.sber.java13spring.java13springproject.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_rent_info")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "book_rent_info_seq", allocationSize = 1)
public class BookRentInfo
        extends GenericModel {
    
    @ManyToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_RENT_BOOK_INFO_BOOK"))
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_RENT_BOOK_INFO_USER"))
    private User user;
    
    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;
    //поле автоматически должно рассчитываться из rent_date + rent_period
    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;
    
    @Column(name = "returned", nullable = false)
    private Boolean returned;
    //rent_period - количество дней аренды, если не указано, то по-умолчанию - 14 дней
    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;
}
