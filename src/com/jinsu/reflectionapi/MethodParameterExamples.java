package com.jinsu.reflectionapi;

// 이 클래스는 Reflection API를 사용하여 클래스 및 열거형의 생성자와 메서드를 출력합니다.
public class MethodParameterExamples {
    
    // InnerClass는 MethodParameterExamples 클래스의 내부 클래스입니다.
    public class InnerClass { }
    
    // Colors는 두 가지 상수(RED, WHITE)를 가진 열거형입니다.
    enum Colors {
        RED, WHITE;
    }
    
    // 메인 메서드: 프로그램의 진입점입니다.
    public static void main(String... args) {
        // InnerClass의 생성자 정보를 출력합니다.
        System.out.println("InnerClass:");
        // MethodParameterSpy 클래스의 printClassConstructors 메서드를 호출하여 InnerClass의 생성자 정보를 출력합니다.
        MethodParameterSpy.printClassConstructors(InnerClass.class);
        
        // Colors 열거형의 생성자 및 메서드 정보를 출력합니다.
        System.out.println("enum Colors:");
        // MethodParameterSpy 클래스의 printClassConstructors 메서드를 호출하여 Colors 열거형의 생성자 정보를 출력합니다.
        MethodParameterSpy.printClassConstructors(Colors.class);
        // MethodParameterSpy 클래스의 printClassMethods 메서드를 호출하여 Colors 열거형의 메서드 정보를 출력합니다.
        MethodParameterSpy.printClassMethods(Colors.class);
    }
}