package com.example.jpaexample.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter @Setter
@ToString
public class Book {
    // Первичный ключ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 250)
    private String title;
    @Column(name = "author", nullable = false, length = 150)
    private String author;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
}







