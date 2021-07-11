package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.Comment;
import com.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void innit(){

        for(int i=0; i<10; i++){
            Comment comment = new Comment();
            comment.setComment("오우야");

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething(){

        List<Comment> comments = commentRepository.findAll();

        for(Comment comment : comments){

            comment.setComment("오히려좋아");

           // commentRepository.save(comment);

        }

    }

    @Transactional
    public void insertSomething(){

        Comment comment = new Comment();

        comment.setComment("뭐하는거냐고지그으으음음");

        commentRepository.save(comment);
   }


}
