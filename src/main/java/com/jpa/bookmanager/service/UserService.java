package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.GenerationType;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUSer");
        user.setEmail("newUser@naver.com");

        //userRepository.save(user);

        //비영속(new)상태태

       entityManager.persist(user);
        //mnaged 상태

//        entityManager.detach(user);
        //detached

        user.setName("newUserAfterPersist");
        entityManager.merge(user);

//        entityManager.flush();
        entityManager.clear();


        User user1 = userRepository.findById(1L).get();

        entityManager.remove(user1);
    }
}
