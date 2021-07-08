package com.jpa.bookmanager.domain;


import javax.persistence.*;

import com.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = AuditingEntityListener.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@DynamicUpdate
@Where(clause = "deleted = false")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long authorId;

    private String category;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id") //중간 테이블 만들지 않기 위해, 나의pk를 상대의 fk로
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>(); //null point exception 방지

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Publisher publisher;

    //@ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>(); //NPE방지하기위해 array생성

    private boolean deleted;

    public void addBookAndAuthors(BookAndAuthor ... bookAndAuthors){ //배열로 받겠다.
        Collections.addAll(this.bookAndAuthors, bookAndAuthors); //book 정보가 여러개 들어오면 한꺼번에 저장.
    }

}
