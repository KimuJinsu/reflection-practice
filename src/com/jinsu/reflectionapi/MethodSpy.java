package com.jinsu.reflectionapi;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import static java.lang.System.out;

public class MethodSpy {
    private static final String fmt = "%24s: %s%n";

    // for the morbidly curious
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String... args) {
        try {
            // args[0]에서 클래스 이름을 받아 Class 객체를 얻습니다.
            Class<?> c = Class.forName(args[0]);
            
            // 클래스의 모든 메서드를 가져옵니다.
            Method[] allMethods = c.getDeclaredMethods();
            
            // 모든 메서드를 순회하며 이름이 args[1]과 일치하는 메서드를 찾습니다.
            for (Method m : allMethods) {
                if (!m.getName().equals(args[1])) {
                    continue;
                }
                
                // 메서드의 전체 서명을 출력합니다.
                out.format("%s%n", m.toGenericString());

                // 반환 타입과 제네릭 반환 타입을 출력합니다.
                out.format(fmt, "ReturnType", m.getReturnType());
                out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                // 파라미터 타입과 제네릭 파라미터 타입을 출력합니다.
                Class<?>[] pType  = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    out.format(fmt, "ParameterType", pType[i]);
                    out.format(fmt, "GenericParameterType", gpType[i]);
                }

                // 예외 타입과 제네릭 예외 타입을 출력합니다.
                Class<?>[] xType  = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for (int i = 0; i < xType.length; i++) {
                    out.format(fmt, "ExceptionType", xType[i]);
                    out.format(fmt, "GenericExceptionType", gxType[i]);
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}