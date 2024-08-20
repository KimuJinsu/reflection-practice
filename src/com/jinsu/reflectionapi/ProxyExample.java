package com.jinsu.reflectionapi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 첫 번째 인터페이스 정의
interface FirstInterface {
    void firstMethod();
}

// 두 번째 인터페이스 정의
interface SecondInterface {
    void secondMethod();
}

// InvocationHandler 구현
class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 프록시 객체의 클래스 정보 출력
        System.out.println("MyInvocationHandler:: invoke : class: " + proxy.getClass());
        
        // 호출된 메서드의 인터페이스에 따라 출력
        if (method.getDeclaringClass().equals(FirstInterface.class)) {
            System.out.println("Invoked method from FirstInterface: " + method.getName());
        } else if (method.getDeclaringClass().equals(SecondInterface.class)) {
            System.out.println("Invoked method from SecondInterface: " + method.getName());
        } else {
            System.out.println("Invoked method from unknown interface: " + method.getName());
        }
        return null;
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        // 클래스 로더를 가져옴
        ClassLoader classLoader = ProxyExample.class.getClassLoader();

        // 프록시 생성 - 인터페이스 순서: FirstInterface, SecondInterface
        Object proxy1 = Proxy.newProxyInstance(
                classLoader,
                new Class<?>[]{FirstInterface.class, SecondInterface.class},
                new MyInvocationHandler()
        );

        // 프록시 생성 - 인터페이스 순서: SecondInterface, FirstInterface
        Object proxy2 = Proxy.newProxyInstance(
                classLoader,
                new Class<?>[]{SecondInterface.class, FirstInterface.class},
                new MyInvocationHandler()
        );

        // 두 프록시의 클래스 정보를 출력
        System.out.println("proxy1 class: " + proxy1.getClass());
        System.out.println("proxy2 class: " + proxy2.getClass());
        
        // 프록시 객체를 각각 인터페이스로 캐스팅
        FirstInterface firstProxy1 = (FirstInterface) proxy1;
        firstProxy1.firstMethod();
                
        SecondInterface secondProxy1 = (SecondInterface) proxy1;
        secondProxy1.secondMethod();
        
        // 두 번째 프록시 객체를 각각 인터페이스로 캐스팅
        FirstInterface firstProxy2 = (FirstInterface) proxy2;
        firstProxy2.firstMethod();
                
        SecondInterface secondProxy2 = (SecondInterface) proxy2;
        secondProxy2.secondMethod();
        
        // 두 프록시 클래스가 같은지 비교
        System.out.println("Are proxy1 and proxy2 classes the same? " + (proxy1.getClass() == proxy2.getClass()));
    }
}