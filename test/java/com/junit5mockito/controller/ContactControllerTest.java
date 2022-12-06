package com.junit5mockito.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit5mockito.entity.Contact;
import com.junit5mockito.service.ContactService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ContactController.class})
@ExtendWith(SpringExtension.class)
class ContactControllerTest {
    @Autowired
    private ContactController contactController;

    @MockBean
    private ContactService contactService;

    @Test
    void testSaveContact() throws Exception {
        Contact contact = new Contact();
        contact.setEmail("jane.doe@example.org");
        contact.setFirstName("Jane");
        contact.setId(123L);
        contact.setLastName("Doe");
        contact.setPhoneNo("4105551212");
        when(contactService.saveContact((Contact) any())).thenReturn(contact);

        Contact contact1 = new Contact();
        contact1.setEmail("jane.doe@example.org");
        contact1.setFirstName("Jane");
        contact1.setId(123L);
        contact1.setLastName("Doe");
        contact1.setPhoneNo("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(contact1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(contactController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"4105551212\"}"));
    }

    /**
     * Method under test: {@link ContactController#getContactById(Long)}
     */
    @Test
    void testGetContactById() throws Exception {
        Contact contact = new Contact();
        contact.setEmail("jane.doe@example.org");
        contact.setFirstName("Jane");
        contact.setId(123L);
        contact.setLastName("Doe");
        contact.setPhoneNo("4105551212");
        when(contactService.getContactById((Long) any())).thenReturn(contact);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contact/{id}", 123L);
        MockMvcBuilders.standaloneSetup(contactController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"4105551212\"}"));
    }

    /**
     * Method under test: {@link ContactController#getContactList()}
     */
    @Test
    void testGetContactList() throws Exception {
        when(contactService.getContactList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contacts");
        MockMvcBuilders.standaloneSetup(contactController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContactController#getContactList()}
     */
    @Test
    void testGetContactList2() throws Exception {
        when(contactService.getContactList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/contacts");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(contactController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContactController#deleteContact(Contact)}
     */
    @Test
    void testDeleteContact() throws Exception {
        doNothing().when(contactService).deleteContact((Contact) any());

        Contact contact = new Contact();
        contact.setEmail("jane.doe@example.org");
        contact.setFirstName("Jane");
        contact.setId(123L);
        contact.setLastName("Doe");
        contact.setPhoneNo("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(contact);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(contactController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

