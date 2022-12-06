package com.junit5mockito.controller;

import com.junit5mockito.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/welcome")
    public ResponseEntity<String>getMessage(){
        String message = welcomeService.getWelcomeMessage();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
