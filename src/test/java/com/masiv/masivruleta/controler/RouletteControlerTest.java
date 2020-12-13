package com.masiv.masivruleta.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masiv.masivruleta.entity.Roulette;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouletteControlerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    private void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void save() {
        Roulette roulette = new Roulette();
        roulette.setId(10);
        roulette.setDescription("The first roulette of the month");
        String jsonRequest = om.writeValueAsString(roulette);
        MvcResult result = mockMvc.perform(

    @Test
    void listRoulettes() {
    }

    @Test
    void openRoullete() {
    }

    @Test
    void closeRoullete() {
    }

    @Test
    void deleteRoulette() {
    }

    @Test
    void makeBet() {
    }
}