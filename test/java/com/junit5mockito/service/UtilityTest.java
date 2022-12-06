package com.junit5mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class UtilityTest {

     @Autowired
     Utils utils;

    @ParameterizedTest
    @ValueSource(strings = {"dad","racecar","madam", "Rabbit","ROM","MOM"})
    public void TestIsPalindrome(String str) throws Exception {
        boolean actual = utils.isPalindrome(str);
        assertTrue(actual);
    }

    @Test
    public void TestIsPalindromeSingle() throws Exception {
        String str = "DAD";
        boolean actual = utils.isPalindrome(str);
        assertTrue(actual);
        verify(utils.isPalindrome(str));
    }
}
