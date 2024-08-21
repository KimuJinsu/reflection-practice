package com.jinsu.dynamicproxy5;

public class MyServiceImpl implements MyService {
    public void performTask() {
        System.out.println("Performing task in MyServiceImpl");
    }
    
    public void anotherTask(String name) {
        System.out.println("Hello, " + name);
    }
}