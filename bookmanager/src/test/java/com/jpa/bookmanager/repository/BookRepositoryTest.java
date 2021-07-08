package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.Publisher;
import com.jpa.bookmanager.domain.Review;
import com.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired //Bookrepository와 연결?
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void bookTest(){
        Book book  = new Book();
        book.setName("에반게리온");
        book.setAuthorId(1L);
       // book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("hyun@naver.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

        //jpa의 relation의 장점은 id로 직접 불러오는게 아니라 getter로 불러울 수 있다는 것. 깰끔쓰
    }

    private void givenBookAndReview(){

        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("hyun@naver.com");
    }

    private Book givenBook(Publisher publisher){
        Book book  = new Book();
        book.setName("초격차");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("네이버");
         return publisherRepository.save(publisher);
    }



    private void givenReview(User user, Book book){
        Review review = new Review();

        review.setTitle("저런..");
        review.setContent("노잼....");
        review.setScore(1.2f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    //@Transactional
    @Test
    public void bookCascadeTest(){

        Book book = new Book();

        book.setName("JPA 공부하자");

        Publisher publisher  = new Publisher();
        publisher.setName("패스트캠");

        book.setPublisher(publisher);
        bookRepository.save(book);

        //System.out.println("books : " + bookRepository.findAll());
        //System.out.println("publishers : " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("탐사수");

        bookRepository.save(book1);
        //System.out.println("publishers : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();

        //bookRepository.delete(book2);

        //publisherRepository.delete(book2.getPublisher());

        Book book3 = bookRepository.findById(1L).get();

        book3.setPublisher(null);

        bookRepository.save(book3);


        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("book3 - publisher : " + bookRepository.findById(1L).get().getPublisher());

        //System.out.println(publisherRepository.findById(1L).get().getBooks());

    }

    @Test
    void bookRemoveCascadeTest(){

        bookRepository.deleteById(1L);

        System.out.println("books : " + bookRepository.findAll());

        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));


    }

    @Test
    void orphanTest(){

        Book book = new Book();
        book.setName("자식");


        Publisher publisher = new Publisher();
        publisher.setName("부모");

        book.setPublisher(publisher);
        publisher.addBook(book);

        bookRepository.save(book);
        publisherRepository.save(publisher);

        //book.setPublisher(null);
        //publisher.getBooks().clear();
        //bookRepository.deleteById(1L);
        //publisherRepository.deleteById(1L);

        //부모 entity에서 자식 entity삭제
        System.out.println(publisher.getBooks().get(0));
       publisher.getBooks().remove(0);

        //bookRepository.save(book);
        publisherRepository.save(publisher);
        //bookRepository.save(book);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("book-publisher : " + bookRepository.findById(1L).get().getPublisher());
        System.out.println("publishers : " + publisherRepository.findAll());
    }


    @Test
    void softDelete(){

        bookRepository.findAll().forEach(System.out::println);

        //bookRepository.findByCategoryIsNull().forEach(System.out::println);

        //bookRepository.findByDeletedFalse().forEach(System.out::println);

    }

}
