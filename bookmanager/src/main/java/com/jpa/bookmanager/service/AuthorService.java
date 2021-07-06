package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Author;
import com.jpa.bookmanager.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import lombok.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public void putAuthor(){

        Author author = new Author();
        author.setName("Hyun");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생했드아. transaction은 어찌될까");
    }


}
