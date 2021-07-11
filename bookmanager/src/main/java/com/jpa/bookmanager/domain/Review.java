package com.jpa.bookmanager.domain;


import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    private float score;

    //book id와 연관관계
    //review 는 book 에 대해서 many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Book book;


    //user 연관관계
    //review는 user에 대해서 many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private List<Comment> comments;


}