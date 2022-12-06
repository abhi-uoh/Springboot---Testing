package com.junit5mockito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit5mockito.entity.Contact;
import com.junit5mockito.repository.MessageDao;
import com.junit5mockito.service.Utils;
import com.junit5mockito.service.WelcomeService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(value = UtilsController.class)
public class UtilsTest {
    

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WelcomeService welcomeService;
    
    @MockBean
    private Utils utils;
    @MockBean
    private MessageDao messageDao;




    @Test
    public void sum() throws Exception {

        int a= 20;
        int b= 30;

        when(utils.getSum(a,b)).thenReturn(50);
        this.mockMvc.perform(get("/sum"))
                .andExpect(status().isOk())
                .andDo(print());
                assertEquals(50,50);

/*        when(welcomeService.getWelcomeMessage()).thenReturn(m);
        when(messageDao.getMessageFromDao()).thenReturn(m);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200, status);
        perform.andDo(print());*/
    }


    /*@Test
    public void TestIsPalindrome() throws Exception {
        String str ="TAT";
        boolean actual = utils.isPalindrome(str);
        this.mockMvc.perform(get("/palindrome"));
        assertTrue(actual);
    }*/

    @Test
    public void ContactSave01() throws Exception{
        when(utils.saveContact(ArgumentMatchers.any())).thenReturn(true);

        Contact contact = new Contact(123L,"Manish","Raj","raj.manish@example.org","4105551212");
        ObjectMapper objectMapper = new ObjectMapper();
        String contactJson = objectMapper.writeValueAsString(contact);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contact-save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson);
        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult result = perform.andReturn();
        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        assertEquals(201,status);
//        verify(utils.saveContact(ArgumentMatchers.any()));

    }

    @Test
    public void ContactSave02() throws Exception{
        when(utils.saveContact(ArgumentMatchers.any())).thenReturn(false);

        Contact contact = new Contact(123L,"Manish","Raj","raj.manish@example.org","4105551212");
        ObjectMapper objectMapper = new ObjectMapper();
        String contactJson = objectMapper.writeValueAsString(contact);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contact-save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson);
        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult result = perform.andReturn();
        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        assertEquals(400,status);

    }

}
