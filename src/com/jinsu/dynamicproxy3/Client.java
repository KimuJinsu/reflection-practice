package com.jinsu.dynamicproxy3;

public class Client {
    public static void main(String[] args) {
        AInterface proxyA = new AProxy(new AImpl());
        proxyA.call();

        BInterface proxyB = new BProxy(new BImpl());
        proxyB.call();
    }
}
