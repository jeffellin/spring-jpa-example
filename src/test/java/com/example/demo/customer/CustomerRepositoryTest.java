package com.example.demo.customer;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testRepo(){
        Address address1 = Address.builder().line1("line1").line2("line2").build();
        Address address2 = Address.builder().line1("line1a").line2("line2a").build();
        List al = new ArrayList<Address>();
        al.add(address1);
        al.add(address2);
        Customer c = new Customer();
        c.setPhoneNumber("2234123123");
        c.setAddress(al);
        repository.save(c);
        List<Customer> actualList = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();

        Customer rCust = customers.iterator().next();
        Assertions.assertTrue(rCust.getAddress().size()==2);

    }

}