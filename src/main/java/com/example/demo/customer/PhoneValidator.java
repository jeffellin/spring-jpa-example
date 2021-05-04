package com.example.demo.customer;

import javax.validation.*;

public class PhoneValidator implements ConstraintValidator<Phone,String> {
   String some = "d";

    public void initialize(Phone parameters) {
        this.some = parameters.id();
    }
    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {

        //System.err.println(some);
        //System.err.println(o);

        return true;
    }
}
