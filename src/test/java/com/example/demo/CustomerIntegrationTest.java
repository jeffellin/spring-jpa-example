package com.example.demo;

import com.example.demo.customer.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This test will actually use the db to connect
 */
@SpringBootTest
public class CustomerIntegrationTest {


    public MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
       //given a new record
       long records =  repository.count();

        String request = "{ " +
                "\"phoneNumber\":\"2234123123\"," +
                "\"firstName\":\"john\"" +
                " }";

        //when adding a record
        this.mvc
                .perform(MockMvcRequestBuilders.post("/c").
                        contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //we should have one more record in the db
        assertEquals(records+1,repository.count());
    }

}
