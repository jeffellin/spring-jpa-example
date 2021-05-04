package com.example.demo.customer;

import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

@Service
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
