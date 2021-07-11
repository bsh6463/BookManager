package com.jpa.bookmanager.service;

import com.jpa.bookmanager.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest(){

        commentService.innit();

        //commentRepository.findAll().forEach(System.out::println);

        commentService.updateSomething();

        //commentService.insertSomething();
    }


}