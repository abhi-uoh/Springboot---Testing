package com.junit5mockito.controller;


import com.junit5mockito.repository.MessageDao;
import com.junit5mockito.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = WelcomeController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WelcomeService welcomeService;
    @MockBean
    private MessageDao messageDao;

    @Test
    public void TestControllerMessage() throws Exception {

        String m = "SayHi";

/*        when(welcomeService.getWelcomeMessage()).thenReturn(m);
        this.mockMvc.perform(get("/welcome")
                .content(new ObjectMapper().writeValueAsString(m)))
                .andExpect(status().isOk())
                .andDo(print());
                assertEquals("SayHi",m);*/

        when(welcomeService.getWelcomeMessage()).thenReturn(m);
        when(messageDao.getMessageFromDao()).thenReturn(m);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status =response.getStatus();
        assertEquals(200,status);
        perform.andDo(print());
    }
}






