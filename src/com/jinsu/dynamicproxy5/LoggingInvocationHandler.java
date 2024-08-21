package com.jinsu.dynamicproxy5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 메서드 호출 전 로깅
        System.out.println("Method " + method.getName() + " called at " + LocalDateTime.now());

        Object result = method.invoke(target, args);

        // 메서드 호출 후 로깅
        System.out.println("Method " + method.getName() + " completed at " + LocalDateTime.now());
        return result;
    }
}