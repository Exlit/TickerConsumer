package com.remote100Test.securityTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.remote100Test.model.TickerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthentificationTest {
    TickerModel tickerModel;
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
        try {
            tickerModel = mapper.readValue("{\"userId\": \"134256\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-Jan-22 10:27:44\", \"originatingCountry\" : \"FR\"}", TickerModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successIfValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tickConsume")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password123"))
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(tickerModel)))
                .andExpect(status().isOk());
    }

    @Test
    void failsIfWrongContentType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tickConsume")
                        .contentType("text/plain")
                        .content(mapper.writeValueAsString(tickerModel)))
                .andExpect(status().isUnauthorized());
    }
}
