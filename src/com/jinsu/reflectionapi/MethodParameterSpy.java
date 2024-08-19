package com.jinsu.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import static java.lang.System.out;

public class MethodParameterSpy {
    
    // 출력 형식을 정의하는 포맷 문자열
    private static final String  fmt = "%24s: %s%n";

    // 제네릭 메서드 (참고용으로 제공됨, 실제로 사용되지 않음)
    <E extends RuntimeException> void genericThrow() throws E {}
    
    // 클래스의 모든 생성자를 출력하는 메서드
    public static void printClassConstructors(Class c) {
        // 클래스의 모든 public 생성자를 가져옴
        Constructor[] allConstructors = c.getConstructors();
        out.format(fmt, "Number of constructors", allConstructors.length);  // 생성자 수 출력
        for (Constructor currentConstructor : allConstructors) {
            printConstructor(currentConstructor);  // 각 생성자 정보를 출력
        }
        
        // 클래스의 모든 선언된 생성자를 가져옴 (private 포함)
        Constructor[] allDeclConst = c.getDeclaredConstructors();
        out.format(fmt, "Number of declared constructors", allDeclConst.length);  // 선언된 생성자 수 출력
        for (Constructor currentDeclConst : allDeclConst) {
            printConstructor(currentDeclConst);  // 각 생성자 정보를 출력
        }
    }
    
    // 클래스의 모든 메서드를 출력하는 메서드
    public static void printClassMethods(Class c) {
        // 클래스의 모든 선언된 메서드를 가져옴 (private 포함)
        Method[] allMethods = c.getDeclaredMethods();
        out.format(fmt, "Number of methods", allMethods.length);  // 메서드 수 출력
        for (Method m : allMethods) {
            printMethod(m);  // 각 메서드 정보를 출력
        }
    }
    
    // 생성자의 세부 정보를 출력하는 메서드
    public static void printConstructor(Constructor c) {
        out.format("%s%n", c.toGenericString());  // 생성자 서명 출력
        Parameter[] params = c.getParameters();  // 생성자의 파라미터 배열 가져옴
        out.format(fmt, "Number of parameters", params.length);  // 파라미터 수 출력
        for (int i = 0; i < params.length; i++) {
            printParameter(params[i]);  // 각 파라미터 정보를 출력
        }
    }
    
    // 메서드의 세부 정보를 출력하는 메서드
    public static void printMethod(Method m) {
        out.format("%s%n", m.toGenericString());  // 메서드 서명 출력
        out.format(fmt, "Return type", m.getReturnType());  // 반환 타입 출력
        out.format(fmt, "Generic return type", m.getGenericReturnType());  // 제네릭 반환 타입 출력
                
        Parameter[] params = m.getParameters();  // 메서드의 파라미터 배열 가져옴
        for (int i = 0; i < params.length; i++) {
            printParameter(params[i]);  // 각 파라미터 정보를 출력
        }
    }
    
    // 파라미터의 세부 정보를 출력하는 메서드
    public static void printParameter(Parameter p) {
        out.format(fmt, "Parameter class", p.getType());  // 파라미터의 타입 출력
        out.format(fmt, "Parameter name", p.getName());  // 파라미터 이름 출력
        out.format(fmt, "Modifiers", p.getModifiers());  // 파라미터의 수정자(예: final) 출력
        out.format(fmt, "Is implicit?", p.isImplicit());  // 암묵적인 파라미터인지 여부 출력
        out.format(fmt, "Is name present?", p.isNamePresent());  // 파라미터 이름이 명시적으로 저장되어 있는지 여부 출력
        out.format(fmt, "Is synthetic?", p.isSynthetic());  // 합성 파라미터인지 여부 출력 (컴파일러가 자동 생성한 파라미터인지)
    }
    
    // 프로그램의 시작점, 클래스 이름을 매개변수로 받아 해당 클래스의 생성자와 메서드를 출력
    public static void main(String... args) {        
        try {
            // 주어진 클래스 이름을 기반으로 클래스를 로드하고, 생성자 및 메서드를 출력
            printClassConstructors(Class.forName(args[0]));
            printClassMethods(Class.forName(args[0]));
        } catch (ClassNotFoundException x) {
            x.printStackTrace();  // 클래스가 없을 경우 예외 처리
        }
    }
}