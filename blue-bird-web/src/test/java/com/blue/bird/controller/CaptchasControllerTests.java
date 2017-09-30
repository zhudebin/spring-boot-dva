package com.blue.bird.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by jim on 2017/9/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CaptchasControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreatCaptchas() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/captchas/image"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
