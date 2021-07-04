package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.repository.AuthorRepository;
import com.jpa.bookmanager.repository.BookRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

   // @Autowired //요즘에는 Autowired보다는...생성자 주입@RequiredArgsConstructor, final이기 때문에 생성자 반드시 필요.
    private final BookRepository bookRepository;

   // @Autowired
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor(){

        Book book = new Book();
        book.setName("JPA 시작작");
        bookRepository.save(book); //DB insert

        Author author = new Author();
        author.setName("hyun");
        authorRepository.save(author); // DB insert

        throw new RuntimeException("오류 발생해서 DB commit 발생하지 않음.");

    }


}
