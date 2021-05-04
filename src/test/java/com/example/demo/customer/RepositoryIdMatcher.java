package com.example.demo.customer;

import com.jayway.jsonpath.*;
import org.hamcrest.*;
import org.springframework.data.repository.*;
import org.springframework.test.util.*;
import org.springframework.test.web.servlet.*;

public class RepositoryIdMatcher extends BaseMatcher<Integer> {

    private CrudRepository crudRepository;

    public RepositoryIdMatcher(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }


    @Override
    public boolean matches(Object o) {
        Long id = Integer.toUnsignedLong((Integer)o);
        Object c = crudRepository.findById(id);
        if(c==null){
            return false;
        }
        return true;
    }

    @Override
    public void describeMismatch(Object o, Description description) {
            description.appendText("Could Not Locate Object with Id:"+o);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("To Find an Object");
    }
}
