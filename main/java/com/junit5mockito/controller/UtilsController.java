package com.junit5mockito.controller;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilsController {

    @Autowired
    private Utils utils;

    @GetMapping("/sum")
    public ResponseEntity<Integer> getSum(){
        Integer sum = utils.getSum(10,20);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @GetMapping("/palindrome")
    public ResponseEntity<Boolean> getPalindrome(){
        boolean palindrome = utils.isPalindrome("MOM");
        return new ResponseEntity<>(palindrome, HttpStatus.OK);
    }

    @PostMapping(value = "/contact-save")
    public ResponseEntity<String> ContactSave(@RequestBody Contact contact) {
        String message = null;
        boolean isSaved = utils.saveContact(contact);
        if (isSaved) {
            message = "Contact Saved Successfully";
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message = "Contact Failed to save ";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        }
    }

}
