package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.repository.AuthorRepository;
import com.jpa.bookmanager.repository.BookRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

   // @Autowired //요즘에는 Autowired보다는...생성자 주입@RequiredArgsConstructor, final이기 때문에 생성자 반드시 필요.
    private final BookRepository bookRepository;

   // @Autowired
    private final AuthorRepository authorRepository;

    private final EntityManager entityManager;

    private final AuthorService authorService;

    @Transactional
    public void putBookAndAuthor(){

        Book book = new Book();
        book.setName("JPA 시작작");
        bookRepository.save(book); //DB insert

        try{ //try catch로 묶지 않으면 exception이 putBookAndAuthor로 전파됨..오류가 전파되지 않도록
            authorService.putAuthor();
        }catch (RuntimeException e){

        }


//        Author author = new Author();
//        author.setName("hyun");
//        authorRepository.save(author); // DB insert
//
       // throw new RuntimeException("오류가 발생했드아. transaction은 어찌될까");

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id){

        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();

//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀌나?");
//        bookRepository.save(book);
    }

    public List<Book> getAll(){

       List<Book> books = bookRepository.findAll();

       books.forEach(System.out::println);

       return books;
    }


}
