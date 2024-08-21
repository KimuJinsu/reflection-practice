package com.jinsu.dynamicproxy4;

import java.lang.reflect.Proxy;

import com.jinsu.dynamicproxy3.AImpl;
import com.jinsu.dynamicproxy3.AInterface;
import com.jinsu.dynamicproxy3.BImpl;
import com.jinsu.dynamicproxy3.BInterface;

public class Client {
    public static void main(String[] args) {
    
        AInterface proxyA = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                new MyProxyHandler(new AImpl())
        );
        proxyA.call();

        BInterface proxyB = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                new MyProxyHandler(new BImpl())
        );
        proxyB.call();
    }
}
