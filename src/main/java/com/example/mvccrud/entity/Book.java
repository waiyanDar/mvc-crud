package com.example.mvccrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @Min(value = 10,message = "Price is too low")
    @Max(value = 100,message = "Price is too high")
    private double price;
    @Past(message = "Year published must be past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;
    @NotBlank
    @NotEmpty(message = "Publisher cannot be empty")
    private String publisher;
    @NotEmpty(message = "Image can't be empty")
    private String imgUrl;

    @ManyToOne
    private Author author;

    @Transient
    private boolean render;
    public Book() {
    }

    public Book(String title, double price, LocalDate yearPublished, String publisher,String imgUrl) {
        this.title = title;
        this.price = price;
        this.yearPublished = yearPublished;
        this.publisher = publisher;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
