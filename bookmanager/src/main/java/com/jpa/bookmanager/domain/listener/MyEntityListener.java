package com.jpa.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {

    @PrePersist //리스너는 해당 entity를 받아서! 처리를 해줘야 한다.
    public void prePersist(Object o){
        if(o instanceof Auditable){
            //Auditable 객체다? 이 리스너가 사용될 엔티티다?
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate //리스너는 해당 entity를 받아서! 처리를 해줘야 한다.
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            //Auditable 객체다? 이 리스너가 사용될 엔티티다?
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
