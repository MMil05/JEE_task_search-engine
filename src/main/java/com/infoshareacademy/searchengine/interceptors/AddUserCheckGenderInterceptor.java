package com.infoshareacademy.searchengine.interceptors;


import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AddUserCheckGenderInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        // context to lista argumentow metody, do ktorej podczepiamy interceptora
        Object[] params = context.getParameters();
        User newUser = (User) params[0];
        if (newUser.getGender() == null) {
            Gender gender;

            if (newUser.getName().endsWith("a"))
                gender = Gender.WOMAN;
            else
                gender = Gender.MAN;

            newUser.setGender(gender);
        }
        return context.proceed();
    }
}
