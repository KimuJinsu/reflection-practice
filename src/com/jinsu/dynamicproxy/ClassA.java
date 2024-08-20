package com.jinsu.dynamicproxy;

public class ClassA implements InterfaceA {
    @Override
    public void duplicateMethod() {
        System.out.println("ClassA: duplicateMethod");
    }
}