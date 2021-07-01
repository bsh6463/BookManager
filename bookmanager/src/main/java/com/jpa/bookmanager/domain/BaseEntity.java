package com.jpa.bookmanager.domain;


import com.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Data
@MappedSuperclass //해당 클래스 필드를 상속받는 entity의 커럶으로 포함시켜줌. 상위 엔티티의 컬럼으로 쓰겠다.
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {
    
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
