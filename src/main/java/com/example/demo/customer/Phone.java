package com.example.demo.customer;

import javax.validation.*;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {
    String message() default "You Suck, So Sorry";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String id();
}
