package com.jinsu.reflectionapi;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import static java.lang.System.out;

enum Spy { BLACK, WHITE }  // 열거형 예시

public class FieldModifierSpy {
    volatile int share;  // 필드 예시
    int instance;        // 필드 예시
    class Inner {}       // 내부 클래스 예시

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);  // 주어진 클래스 이름으로 Class 객체를 가져옴
            int searchMods = 0x0;  // 검색할 접근 제어자들을 저장할 변수

            // args[1]부터의 인자를 접근 제어자 문자열로 변환하여 searchMods에 추가
            for (int i = 1; i < args.length; i++) {
                searchMods |= modifierFromString(args[i]);
            }

            // 주어진 클래스의 모든 필드 가져오기
            Field[] flds = c.getDeclaredFields();
            out.format("Fields in Class '%s' containing modifiers:  %s%n",
                       c.getName(),
                       Modifier.toString(searchMods));  // 검색할 접근 제어자 문자열로 출력
            boolean found = false;  // 필드를 찾았는지 여부를 표시하는 변수
            for (Field f : flds) {
                int foundMods = f.getModifiers();  // 필드의 접근 제어자 가져오기
                // 검색된 필드의 접근 제어자가 검색할 접근 제어자들을 모두 포함하는지 검사
                if ((foundMods & searchMods) == searchMods) {
                    out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
                               f.getName(), f.isSynthetic(), f.isEnumConstant());
                    found = true;
                }
            }

            if (!found) {
                out.format("No matching fields%n");  // 조건에 맞는 필드가 없을 경우 메시지 출력
            }

        // 생산 코드에서는 이 예외를 더 적절하게 처리해야 함
        } catch (ClassNotFoundException x) {
            x.printStackTrace();  // 클래스가 없을 경우 예외 출력
        }
    }

    // 접근 제어자 문자열을 Modifier 상수로 변환하는 메서드
    private static int modifierFromString(String s) {
        int m = 0x0;
        if ("public".equals(s))           m |= Modifier.PUBLIC;
        else if ("protected".equals(s))   m |= Modifier.PROTECTED;
        else if ("private".equals(s))     m |= Modifier.PRIVATE;
        else if ("static".equals(s))      m |= Modifier.STATIC;
        else if ("final".equals(s))       m |= Modifier.FINAL;
        else if ("transient".equals(s))   m |= Modifier.TRANSIENT;
        else if ("volatile".equals(s))    m |= Modifier.VOLATILE;
        return m;
    }
}