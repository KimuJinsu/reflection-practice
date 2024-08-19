package com.jinsu.reflectionapi;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import static java.lang.System.out;

public class ConstructorAccess {
    public static void main(String... args) {
        try {
            // 1. 명령줄 인자로 전달된 클래스 이름을 사용하여 클래스를 동적으로 로드합니다.
            Class<?> c = Class.forName(args[0]);

            // 2. 로드한 클래스에서 모든 생성자를 가져옵니다.
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors) {
                // 3. 명령줄 인자로 전달된 접근 제어자 문자열을 정수로 변환합니다.
                int searchMod = modifierFromString(args[1]);
                // 4. 현재 생성자의 접근 제어자를 정수로 가져옵니다.
                int mods = accessModifiers(ctor.getModifiers());
                // 5. 현재 생성자의 접근 제어자가 검색된 접근 제어자와 일치하는 경우
                if (searchMod == mods) {
                    // 6. 현재 생성자의 제네릭 문자열 표현을 출력합니다.
                    out.format("%s%n", ctor.toGenericString());
                    // 7. 생성자가 synthetic(자바 컴파일러에 의해 생성된)인지, varargs(가변 인자)인지 출력합니다.
                    out.format("  [ synthetic=%-5b var_args=%-5b ]%n",
                               ctor.isSynthetic(), ctor.isVarArgs());
                }
            }

            // 실제 코드에서는 이러한 예외를 더 우아하게 처리해야 합니다.
        } catch (ClassNotFoundException x) {
            x.printStackTrace(); // 클래스가 발견되지 않았을 때 예외 처리
        }
    }

    // 8. 주어진 접근 제어자 플래그에서 public, protected, private 접근 제어자만 추출합니다.
    private static int accessModifiers(int m) {
        return m & (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED);
    }

    // 9. 접근 제어자 문자열을 Modifier 클래스의 정수 값으로 변환합니다.
    private static int modifierFromString(String s) {
        if ("public".equals(s))               return Modifier.PUBLIC;
        else if ("protected".equals(s))       return Modifier.PROTECTED;
        else if ("private".equals(s))         return Modifier.PRIVATE;
        else if ("package-private".equals(s)) return 0; // 패키지-프라이빗은 0으로 처리
        else return -1; // 인식할 수 없는 문자열
    }
}