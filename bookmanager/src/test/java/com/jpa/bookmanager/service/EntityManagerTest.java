package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest(){

        System.out.println(entityManager.createQuery("select u from User u").getResultList());
        //u라는 User ENtity를 user의 정볼르 모두 가져와라.
        // = userRepository.findAll();

    }

    @Test
    void cacheFindTest(){

        System.out.println(userRepository.findByEmail("hyun@naver.com"));
       System.out.println(userRepository.findByEmail("hyun@naver.com"));
       System.out.println(userRepository.findByEmail("hyun@naver.com"));
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());

      //  userRepository.deleteById(1L);

    }

    @Test
    void cacheFindTest2(){

        User user = userRepository.findById(1L).get();
       user.setName("hyhyhyhyhy");

       userRepository.save(user);

        System.out.println("----------------------------");

       user.setEmail("hyhyhyhyhy@anver.com");

       userRepository.save(user);

        System.out.println(userRepository.findAll());

    }


}
