package com.jpa.bookmanager.domain;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private String country;

    //@ManyToMany
    @OneToMany
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor ... bookAndAuthors){ //배열로 받겠다.
      Collections.addAll(this.bookAndAuthors, bookAndAuthors); //book 정보가 여러개 들어오면 한꺼번에 저장.
    }

}
