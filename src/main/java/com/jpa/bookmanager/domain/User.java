package com.jpa.bookmanager.domain;

import com.jpa.bookmanager.domain.listener.Auditable;
import com.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Builder
@Entity //Entity에는 primary key가 꼭 필요함., JPA가 관리하고 있는 객체
@Table
@Data
@EntityListeners(value = {UserEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @NonNull
    private String name;


    @NonNull
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 하나씩 증가함.
    private Long id;

    //이 testData는 DB에 반영하기 않고 사용하고 싶은 object data일 수 있음.
    @Transient //영속성 처리에서 제외되어 db data에 반영되지 않고 해당 객체와 생명주기 같이합.
    private String testData;


    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    @OneToMany(fetch = FetchType.EAGER) //이후에 자세히
    @JoinColumn(name = "user_id", insertable = false, updatable = false) //엔티티가 어떤 컬럼으로 조인을 하게 될지 지정해주는 annotation
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();



}
