package com.junit5mockito.service;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactServiceTestS {

  @MockBean
  ContactRepository contactRepository;

  @Autowired
  ContactService contactService;


    @Test
    public void test_saveContact(){
        Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
        when(contactRepository.save(contact)).thenReturn(contact);
        assertEquals(contact,contactService.saveContact(contact));
    }
    @Test
    public void test_getContactById(){
        Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
        when(contactRepository.findById(100L)).thenReturn(Optional.of(contact));
        assertEquals("lipsa@gmail.com",contactService.getContactById(100L).getEmail());

    }
    @Test
    public  void test_getContactList(){
        List<Contact> contactList = Arrays.asList(new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456"),
                new Contact(101L,"Robert","Frost","robert@gmail.com","56789"));
        when(contactRepository.findAll()).thenReturn(contactList);
        assertEquals(2,contactService.getContactList().size());

    }

    @Test
    public void test_deleteContact(){
        Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
        contactService.deleteContact(contact);
        verify(contactRepository,times(1)).delete(contact);
    }
}
