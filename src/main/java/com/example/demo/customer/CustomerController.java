package com.example.demo.customer;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.*;
import java.util.*;


@RestController
@RequestMapping("/c")
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return repo.save(customer);
    }


    @Autowired
    EntityManager entityManager;
    @GetMapping
    public List<Customer> search(@RequestParam Map<String,String> params) throws Exception {

        if(params.isEmpty()){
            return Collections.emptyList();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> studentRoot = criteriaQuery.from(Customer.class);
        criteriaQuery.select(studentRoot);

        params.forEach((k,v)->{
            Predicate predicateName = criteriaBuilder.equal(studentRoot.get(k), v);
            criteriaQuery.where(predicateName);
        });


        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Customer> studentList = typedQuery.getResultList();
        return studentList;
    }



}
