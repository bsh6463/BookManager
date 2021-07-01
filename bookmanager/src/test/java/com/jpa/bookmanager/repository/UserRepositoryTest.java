package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Gender;
import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void crud() {//create, read, update, delete

        userRepository.save(new User("david", "david@fast.com"));//insser query동작

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("sdfsdf-updated@naver.com");

        userRepository.save(user);

    }

    @Test
    void select() {
/*        System.out.println(userRepository.findByName("hyun"));
        System.out.println("find by Email : "+ userRepository.findByEmail("hyun@google.com"));
        System.out.println("get by Email : "+ userRepository.getByEmail("hyun@google.com"));
        System.out.println("read by Email : "+ userRepository.readByEmail("hyun@google.com"));
        System.out.println("search by Email : "+ userRepository.searchByEmail("hyun@google.com"));
        System.out.println("stream by Email : "+ userRepository.streamByEmail("hyun@google.com"));
        System.out.println("find Users by Email : "+ userRepository.findUsersByEmail("hyun@google.com"));
        System.out.println("find Users by Email : "+ userRepository.findUsersByEmail("hyun@google.com"));
        System.out.println("find top 1 by Name : " + userRepository.findTop1ByName("hyun"));
        System.out.println("find first 1 by Name : " + userRepository.findFirst1ByName("hyun"));
        System.out.println("find last 1 by Name : " + userRepository.findLast1ByName("hyun"))'


        System.out.println("find by email and name : " + userRepository.findByEmailAndName("hyun@google.com", "hyun"));
        System.out.println("find by email or name : " + userRepository.findByEmailOrName("hyun@google.com", "kim"));

        System.out.println("find by created at After : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("find by created at Before : " + userRepository.findByCreatedAtBefore(LocalDateTime.now().minusDays(1L)));

        System.out.println("find by id after : " + userRepository.findByIdAfter(3L));

        System.out.println("find by created at Greater than  : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));

        System.out.println("find by created at Between : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("find by id at Between : " + userRepository.findByIdBetween(2L, 4L));

        */

        //System.out.println("find by id is not null : " + userRepository.findByIdIsNotNull());
        //System.out.println("find by id is not empty : " + userRepository.findByAddressIsNotEmpty());

        //System.out.println("find by name in : " + userRepository.findByNameIn(Lists.newArrayList("hyun", "kim")));

        System.out.println("find by name staring with : " + userRepository.findByNameStartingWith("hy"));
        System.out.println("find by name ending with : " + userRepository.findByNameEndingWith("un"));
        System.out.println("find by name containing : " + userRepository.findByNameContaining("k"));


    }

    @Test
    void pagingAndSortingTest() {

        /*System.out.println("find top 1 by name : " + userRepository.findTop1ByName("hyun"));
        System.out.println("find last 1 by name : " + userRepository.findTop1ByNameOrderByIdDesc("hyun"));
        System.out.println("find last  by name : " + userRepository.findTopByNameOrderByIdDesc("hyun"));
        System.out.println("find last  2 by name : " + userRepository.findTop2ByNameOrderByIdDesc("hyun"));
        */
        System.out.println("find first by name order by id desc Email Asc : "
                + userRepository.findFirstByNameOrderByIdDescEmailAsc("hyun"));

        System.out.println("find first by name with sort param : "
                + userRepository.findFistByName("hyun", getSort()));

        System.out.println("find by name with paging, get totla elements: " + userRepository.findByName("hyun", PageRequest.of(0, 2, Sort.by(Sort.Order.desc("id")))).getTotalElements());

    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt")
        );
    }


    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("marti2@fastcompus.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow();
        user2.setName("Marrrrrrrtin");

        userRepository.save(user2);

    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest(){

        User user = new User();
        user.setEmail("hyun10@navber.com");
        user.setName("hyun10");

        userRepository.save(user);


        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("hyyyyyyyyyyyyyyun");

        userRepository.save(user2);


        userRepository.deleteById(4L);


    }


    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("hyun10@navver.com");
        user.setName("hyun10");
        //user.setCreatedAt(LocalDateTime.now());
        //user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("hyun10@navver.com"));

    }

    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as - is : " + user);

        user.setName("lalalalalalalala");

        userRepository.save(user);

        System.out.println(" to be : " + userRepository.findAll().get(0));//이후 영속성 공부시...
    }

    @Test
    void userHistoryTest(){
        User user  = new User();
        user.setEmail("hyun19@navernaver.com");
        user.setName("hyun19");

        userRepository.save(user);

        user.setName("hyun20_new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }


    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("David");
        user.setEmail("dabid@navergoogle.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@navergoogle.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

      //  List<UserHistory> result = userHistoryRepository.findByUserId(userRepository.findByEmail("daniel@navergoogle.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@navergoogle.com")
                                    .getUserHistories();

        result.forEach(System.out::println);

        System.out.println("==============================");

        System.out.println("UserHistory.getUser() : " + userHistoryRepository.findAll().get(0));
    }
}