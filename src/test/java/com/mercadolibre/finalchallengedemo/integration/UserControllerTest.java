package com.mercadolibre.finalchallengedemo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserControllerTest extends ControllerTest {

    private static final String PATH = "/api/v1";


    @Autowired
    MockMvc mockMvc;

    @Test
    void loginFail() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post( "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("user", "user_one")
                .queryParam("password", "contra12"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void loginOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(  "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("user", "gembleton0")
                .queryParam("password", "IH4YH7kAk"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
