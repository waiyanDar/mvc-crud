package com.example.mvccrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "name cannot be empty!")
    @Pattern(regexp = "[A-Z a-z]*",message = "Name cannot contain illegal characters.")
    private String name;


    @Past(message = "Date of birth must be past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Email(message = "Invalid Email Format")
    private String email;


    @OneToMany(mappedBy = "author",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, LocalDate dateOfBirth,String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    public void addBook(Book book){
        book.setAuthor(this);
        books.add(book);
    }
    public void removeBook(Book book){
        book.setAuthor(null);
        books.remove(book);
    }
}
