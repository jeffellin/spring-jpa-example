package com.example.demo.customer;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;


@RestController
@RequestMapping("/c")
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return repo.save(customer);
    }


}
