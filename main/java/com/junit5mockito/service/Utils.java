package com.junit5mockito.service;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    @Autowired
    ContactRepository contactRepository;
    public Integer getSum(Integer a, Integer b){
         Integer c = a+b;
         return c;
    }

    public boolean isPalindrome(String str){
        String reverse = "";
        int length = str.length();
        for (int i = length-1; i>= 0; i--){
            reverse = reverse+str.charAt(i);
        }
        if(str.equals(reverse)){
            return true;
        }else {
            return false;
        }
    }


    public boolean saveContact(Contact contact) {
            return true;
    }
}
