package com.jpa.bookmanager.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookAndAuthor extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;
}
