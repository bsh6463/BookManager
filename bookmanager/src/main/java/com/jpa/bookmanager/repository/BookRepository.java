package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {//enum타입, id타입



}
