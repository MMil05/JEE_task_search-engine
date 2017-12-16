package com.infoshareacademy.searchengine.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class AddUserLogInterceptor {

    Logger logger = Logger.getLogger(AddUserLogInterceptor.class.getName());

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        logger.info("Add user has been invoked!");
        return context.proceed();
    }

}
