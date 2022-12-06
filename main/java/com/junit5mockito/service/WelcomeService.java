package com.junit5mockito.service;

import com.junit5mockito.repository.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    @Autowired
    private MessageDao messageDao;

    public String getWelcomeMessage() {

        return messageDao.getMessageFromDao();
    }
}
