package com.jinsu.dynamicproxy3;

public class BImpl implements BInterface {
    @Override
    public String call() {
        System.out.println("B 호출");
        return "b";
    }
}
