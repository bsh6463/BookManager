package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {//enum타입, id타입

    //PHANTOM READ강제로 발생,custom query
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();



}
