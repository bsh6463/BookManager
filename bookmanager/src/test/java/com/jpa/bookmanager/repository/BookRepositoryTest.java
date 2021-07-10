package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Book;
import com.jpa.bookmanager.domain.Publisher;
import com.jpa.bookmanager.domain.Review;
import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.repository.dto.BookStatus;
import org.hibernate.result.UpdateCountOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

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

    private EntityManager entityManager;


    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("에반게리온");
        book.setAuthorId(1L);
        // book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("hyun@naver.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

        //jpa의 relation의 장점은 id로 직접 불러오는게 아니라 getter로 불러울 수 있다는 것. 깰끔쓰
    }

    private void givenBookAndReview() {

        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        return userRepository.findByEmail("hyun@naver.com");
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("초격차");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("네이버");
        return publisherRepository.save(publisher);
    }


    private void givenReview(User user, Book book) {
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
    public void bookCascadeTest() {

        Book book = new Book();

        book.setName("JPA 공부하자");

        Publisher publisher = new Publisher();
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
    void bookRemoveCascadeTest() {

        bookRepository.deleteById(1L);

        System.out.println("books : " + bookRepository.findAll());

        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));


    }


    @Test
    void orphanTest() {


        Book book = new Book();
        book.setName("자식");

        //Book book2  = new Book();
        //book2.setName("자식2");


        Publisher publisher = new Publisher();
        publisher.setName("부모");

        book.setPublisher(publisher);
        publisher.addBook(book);
        //publisher.addBook(book2);

        bookRepository.save(book);
        //bookRepository.save(book2);
        publisherRepository.save(publisher);


        //book과 publisher 연관관계 삭제 -> orphan removal에 의한 book delete
        //book.setPublisher(null);
        //publisher.getBooks().clear();
        //bookRepository.deleteById(1L);
        //publisherRepository.deleteById(1L);

        //부모 entity에서 자식 entity삭제
        //System.out.println(publisher.getBooks().get(0));
        publisher.getBooks().remove(0);


        //bookRepository.save(book);
        publisherRepository.save(publisher);
        //bookRepository.save(book);


        System.out.println("books : " + bookRepository.findAll());
        //System.out.println("book-publisher : " + bookRepository.findById(1L).get().getPublisher());
        System.out.println("publishers : " + publisherRepository.findAll());
    }


    @Test
    void softDelete() {

        bookRepository.findAll().forEach(System.out::println);

        //bookRepository.findByCategoryIsNull().forEach(System.out::println);

        //bookRepository.findByDeletedFalse().forEach(System.out::println);

    }

    @Test
    void queryTest() {

//        System.out.println("findByCategoryIsNullAndNameEqualsANdCreatedAtGreaterThanEqualAndUpdatedAtGraeaterThanEqual : "
//        + bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
//                "삼다수", LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)));

        //  System.out.println("findByNameRecently : " + bookRepository.findByNameRecently("삼다수", LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L)));

        //System.out.println("name : category");
//       bookRepository.findBookNameAndCategory().forEach(b-> {
//           System.out.println(b.getName() + " : " + b.getCategory());
//       });

       bookRepository.findBookNameAndCategory(PageRequest.of(1,1)).forEach(
               bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));

       bookRepository.findBookNameAndCategory(PageRequest.of(0, 1)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));

    }

    @Test
    void nativeQueryTest(){

//        System.out.println("JPQL : findAll");
//        bookRepository.findAll().forEach(System.out::println);
//
//        System.out.println("SQL : findAllCustom");
//        bookRepository.findAllCustom().forEach(System.out::println);

        List<Book> books = bookRepository.findAll();
        for(Book book : books){

            book.setCategory("백엔드드");

       }
        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affected rows : " + bookRepository.updateCategories());
        System.out.println(bookRepository.findAllCustom());

        System.out.println(bookRepository.showTables());


    }


    @Test
    void converterTest(){

        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("프론트");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        //DB에 잘 들어갔는지 보고싶은데....?DB값을 보고싶은데..?--> native query
        System.out.println(bookRepository.findRawRecord().values());

    }
}
