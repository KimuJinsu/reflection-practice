package com.jinsu.dynamicproxy3;

public class AImpl implements AInterface {
    @Override
    public String call() {
        System.out.println("A 호출");
        return "a";
    }
}
