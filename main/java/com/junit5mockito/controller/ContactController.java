package com.junit5mockito.controller;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return ResponseEntity.ok(contactService.saveContact(contact));
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        return ResponseEntity.ok(contactService.getContactById(id));
    }

  /*  @GetMapping("/contact/{name}")
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable String name){
        return ResponseEntity.ok(contactService.getByName(name));
    }*/

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContactList(){
        return ResponseEntity.ok(contactService.getContactList());
    }

    @DeleteMapping("/contact")
    public void deleteContact(@RequestBody Contact contact){
       contactService.deleteContact(contact);
    }

}
