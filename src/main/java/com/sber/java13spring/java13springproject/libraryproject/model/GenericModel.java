package com.sber.java13spring.java13springproject.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericModel implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    private Long id;
    
    @Column(name = "created_when", nullable = false)
    private LocalDateTime createdWhen;
    
    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
