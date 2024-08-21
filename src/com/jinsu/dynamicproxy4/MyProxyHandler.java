package com.jinsu.dynamicproxy4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class MyProxyHandler implements InvocationHandler {
    private final Object target;

    MyProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("TimeProxy 실행");
        long startTime = System.nanoTime();

        Object result = method.invoke(target, args); // 파라미터로 전달받은 메서드를 invoke로 실행

        long endTime = System.nanoTime();
        long resultTime = endTime - startTime;
        System.out.println("TimeProxy 종료 resultTime = " + resultTime);

        return result;
    }
}
