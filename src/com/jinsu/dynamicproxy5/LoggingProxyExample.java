package com.jinsu.dynamicproxy5;

import java.lang.reflect.Proxy;

public class LoggingProxyExample {
    public static void main(String[] args) {
        MyService service = new MyServiceImpl();
        MyService proxyInstance = (MyService) Proxy.newProxyInstance(
            service.getClass().getClassLoader(),
            new Class[]{MyService.class},
            new LoggingInvocationHandler(service)
        );

        proxyInstance.performTask();
        proxyInstance.anotherTask("Alice");
    }
}