package com.jpa.bookmanager.domain.listener;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.domain.UserHistory;
import com.jpa.bookmanager.repository.UserHistoryRepository;
import com.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//@Component //bean으로 등록
public class UserEntityListener {


   // @Autowired
    //private UserHistoryRepository userHistoryRepository;
    @PostPersist
    @PostUpdate
    public void prePersistpreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        //userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);


    }
}
