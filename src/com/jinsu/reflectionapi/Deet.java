package com.jinsu.reflectionapi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;
import static java.lang.System.out;
import static java.lang.System.err;

public class Deet<T> {

    // 비공개 메서드: 특정 Locale 객체의 정보 출력 후 true를 반환
    private boolean testDeet(Locale l) {
        // getISO3Language()는 MissingResourceException을 던질 수 있음
        out.format("Locale = %s, ISO Language Code = %s%n", l.getDisplayName(), l.getISO3Language());
        return true;
    }

    // 비공개 메서드: Locale을 파라미터로 받아 0을 반환
    private int testFoo(Locale l) { return 0; }

    // 비공개 메서드: 파라미터 없이 true를 반환
    private boolean testBar() { return true; }

    public static void main(String... args) {
        // 인자가 4개가 아니면 잘못된 입력으로 간주하고 사용법 출력
        if (args.length != 4) {
            err.format("Usage: java Deet <classname> <langauge> <country> <variant>%n");
            return;
        }

        try {
            // 첫 번째 인자로 전달된 클래스 이름으로 클래스 로드
            Class<?> c = Class.forName(args[0]);

            // 로드한 클래스의 인스턴스 생성
            Object t = c.newInstance();

            // 해당 클래스의 모든 메서드 검색
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                String mname = m.getName();

                // 메서드 이름이 "test"로 시작하지 않거나 반환 타입이 boolean이 아니면 무시
                if (!mname.startsWith("test") || (m.getGenericReturnType() != boolean.class)) {
                    continue;
                }

                // 메서드의 파라미터 타입이 1개가 아니거나 Locale 클래스를 상속받지 않으면 무시
                Type[] pType = m.getGenericParameterTypes();
                if ((pType.length != 1) || Locale.class.isAssignableFrom(pType[0].getClass())) {
                    continue;
                }

                // 해당 메서드를 호출
                out.format("invoking %s()%n", mname);
                try {
                    m.setAccessible(true); // 비공개 메서드도 접근 가능하게 설정
                    Object o = m.invoke(t, new Locale(args[1], args[2], args[3])); // Locale 객체를 파라미터로 전달
                    out.format("%s() returned %b%n", mname, (Boolean) o); // 호출 결과 출력

                } catch (InvocationTargetException x) {
                    Throwable cause = x.getCause(); // 메서드가 던진 예외를 처리
                    err.format("invocation of %s failed: %s%n", mname, cause.getMessage());
                }
            }

        } catch (ClassNotFoundException x) {
            // 클래스가 발견되지 않았을 때 예외 처리
            x.printStackTrace();
        } catch (InstantiationException x) {
            // 인스턴스 생성 중 예외 처리
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            // 메서드 접근 권한이 없을 때 예외 처리
            x.printStackTrace();
        }
    }
}