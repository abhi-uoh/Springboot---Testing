package com.junit5mockito.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {
    public String getMessageFromDao(){
        return "Message from Dao";
    }
}
