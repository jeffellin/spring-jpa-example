package com.example.demo.customer;

import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

import java.util.*;


public interface CustomerRepository extends CrudRepository<Customer,Long> {


}
