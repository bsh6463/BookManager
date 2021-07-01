package com.jpa.bookmanager.domain;
import com.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(name = "user_id", insertable = false, updatable = false)
    //private Long userId;

    private String name;

    private String email;

    @ManyToOne
    //@ToString.Exclude
    private User user;


}
