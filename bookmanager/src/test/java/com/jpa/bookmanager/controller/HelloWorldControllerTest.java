package com.jpa.bookmanager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest //sliced test 전체 spring context를 로드하는게 아니라, 일부분만.
//@SpringBootTest
//@MockBean(JpaMetamodelMappingContext.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello-world"));

    }

}