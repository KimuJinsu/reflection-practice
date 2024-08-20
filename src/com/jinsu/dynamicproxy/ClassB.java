package com.jinsu.dynamicproxy;

public class ClassB implements InterfaceB {
    @Override
    public void duplicateMethod() {
        System.out.println("ClassB: duplicateMethod");
    }
}