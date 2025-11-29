package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPrimeNumberOrNotValidator implements ConstraintValidator<CheckPrimeNumberOrNot,Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return isPrime(integer);
    }

    private boolean isPrime(Integer integer) {
        if(integer<=1)return false;
        if(integer<=3)return true;
        if (integer % 2 == 0 || integer % 3 == 0)
            return false;
        for(int i=5;i*i<=integer;i=i+6){
            if(integer%i==0 || integer%(i+2)==0){
                return false;
            }
        }
        return true;
    }
}
