package com.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setEmail("sdflsdf@lsdfkjsldf.com");
        user.setName("sldkfjsdlfkj");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

       User user3 =  User.builder().name("sdlkjsdf").email("sdlfkjsdf.com").build();

       // User user1 = new User("name", "sdlfksdf@sdlfksdf.com", LocalDateTime.now(), LocalDateTime.now(), null);

        System.out.println(">>>>>" + user.toString());
    }

}