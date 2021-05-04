package com.example.demo;

import com.example.demo.customer.*;
import com.jayway.jsonpath.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.data.repository.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
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
                "\"firstName\":\"john\"," +
                "\"address\" : [{\"line1\":\"foo\"}]" +
                " }";

        //when adding a record

        MvcResult mvcResult = this.mvc
                .perform(MockMvcRequestBuilders.post("/c").
                        contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", newObjectExistsinRepository(repository)))
                .andReturn();

        //don't need to do this since we are doing the same with the
        //newObjectExistsinRepository but we may want to do additional
        //checks like the name match.
        String result = mvcResult.getResponse().getContentAsString();
        Integer id = JsonPath.read(result,"$.id");

        Optional<Customer> c = repository.findById( Integer.toUnsignedLong(id));
        assertEquals(c.get().getFirstName(),"john");
        assertTrue(c.get().getAddress().size()==1);

        //we should have one more record in the db
        assertEquals(records+1,repository.count());
    }

    public static  RepositoryIdMatcher newObjectExistsinRepository(CrudRepository repository) {
        return new RepositoryIdMatcher(repository);
    }

}
