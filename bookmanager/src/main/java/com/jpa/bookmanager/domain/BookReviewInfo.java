package com.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //private long bookId;
    @OneToOne(optional = false) //1:1 연관관계 맵핑
    private Book book;
    //테이블에는 book id라는 long값이 실제로 존재하지만
    //jpa에서 entity로 set/get을 하면 relation을 자동으로 맺어줌.


    private float averageReviewScore;
    //flot, Flot 차이? null 허용 여부

    private int reviewCount;


}
