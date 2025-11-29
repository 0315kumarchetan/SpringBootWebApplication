package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = {CheckPrimeNumberOrNotValidator.class})
public @interface CheckPrimeNumberOrNot {
    String message() default "This is not prime number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
