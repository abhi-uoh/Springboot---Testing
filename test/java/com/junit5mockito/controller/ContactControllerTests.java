package com.junit5mockito.controller;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ContactController.class)
public class ContactControllerTests {

    @MockBean
    ContactService contactService;
    @Autowired
    MockMvc mockMvc;

     @Test
    public void test_saveContact() throws Exception {
         Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
         when(contactService.saveContact(contact)).thenReturn(contact);
         mockMvc.perform(post("/contact")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(new ObjectMapper().writeValueAsString(contact)))
                 .andExpect(status().isOk());

     }
    @Test
    public void test_getContactById() throws Exception {
        Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
        when(contactService.getContactById(100L)).thenReturn(contact);
        mockMvc.perform(get("/contact/{id}",100L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName").value("Lipsa"));
    }

    @Test
    @Order(1)
    public  void test_getContactList() throws Exception {
        List<Contact> contactList = Arrays.asList(new Contact(100L, "Lipsa", "Patra", "lipsa@gmail.com", "123456"),
                new Contact(101L, "Robert", "Frost", "robert@gmail.com", "56789"));

        when(contactService.getContactList()).thenReturn(contactList);
        mockMvc.perform(get("/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Lipsa"));


    }

    @Test
    public void test_deleteContact() throws Exception {
        Contact contact = new Contact(100L,"Lipsa","Patra","lipsa@gmail.com","123456");
        doNothing().when(contactService).deleteContact(contact);
        mockMvc.perform(delete("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contact)))
                .andExpect(status().isOk());


    }


    }
