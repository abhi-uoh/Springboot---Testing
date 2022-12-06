package com.junit5mockito.controller;

import static org.mockito.Mockito.when;

import com.junit5mockito.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {WelcomeController.class})
@ExtendWith(SpringExtension.class)
class WelcomeControllerTest {
    @Autowired
    private WelcomeController welcomeController;

    @MockBean
    private WelcomeService welcomeService;

    /**
     * Method under test: {@link WelcomeController#getMessage()}
     */
    @Test
    void testGetMessage() throws Exception {
        when(welcomeService.getWelcomeMessage()).thenReturn("Welcome Message");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Message"));
    }

    /**
     * Method under test: {@link WelcomeController#getMessage()}
     */
    @Test
    void testGetMessage2() throws Exception {
        when(welcomeService.getWelcomeMessage()).thenReturn("Welcome Message");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/welcome");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Message"));
    }
}

