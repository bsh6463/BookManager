package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
                                                    //<@Entity 타입, Id 타입>

    Set<User> findByName(String name);
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);
    User findUsersByEmail(String email);
    User findSomethingByEmail(String email);
    List<User> findFirst1ByName(String name);
    //List<User> findTop1ByName(String name);
    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterDay);
    List<User> findByCreatedAtBefore(LocalDateTime yesterDay);

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdIsNotNull();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContaining(String name);

   // Set<User> findUserByNameIs(String name);
    //Set<User> findUserByName(String name);
   // Set<User> findUserEquals(String name);

    List<User> findTop1ByName(String name);
    List<User> findTop1ByNameOrderByIdDesc(String name); //id 역순으로 정렬해서 같은 이름중 id가장 큰애
    List<User> findTopByNameOrderByIdDesc(String name); //id 역순으로 정렬해서 같은 이름중 id가장 큰애
    List<User> findTop2ByNameOrderByIdDesc(String name); //id 역순으로 정렬해서 같은 이름중 id가장 큰애

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFistByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();
    //value의 값이

    @Query(value = "select * from user", nativeQuery = true)
    List<Map<String, Object>> findAllRawRecord();

}
