package com.example.mvccrud.dao;

import com.example.mvccrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends JpaRepository<Book,Integer> {
}
