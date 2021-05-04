package com.example.demo.customer;

import com.example.demo.customer.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import static org.mockito.Mockito.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository userService;


    /**
     * Area codes start with a number from 2–9, followed by 0–8, and then any third digit.
     * The second group of three digits, known as the central office or exchange code,
     * starts with a number from 2–9, followed by any two digits The final four digits,
     * known as the station code, have no restrictions.
     * @throws Exception
     */
    @Test
    public void shouldCreateCustomer() throws Exception {

        String request = "{ " +
                  "\"phoneNumber\":\"2234123123\"" +
                " }";


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/c").
                        contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService, times(1)).save(any(Customer.class));

    }
    @Test
    public void shouldNotCreateCustomer() throws Exception {

        String request = "{ " +
                "\"phoneNumber\":\"123e4123123\"" +
                " }";


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/c").
                        contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

}
