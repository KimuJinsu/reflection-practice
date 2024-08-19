package com.jinsu.reflectionapi;

import static java.lang.System.out;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class ConstructorSift {
    public static void main(String... args) {
        try {
            // 1. 두 번째 인자로 전달된 클래스를 로드합니다.
            Class<?> cArg = Class.forName(args[1]);

            // 2. 첫 번째 인자로 전달된 클래스를 로드합니다.
            Class<?> c = Class.forName(args[0]);

            // 3. 로드한 클래스에서 모든 생성자를 가져옵니다.
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors) {
                // 4. 각 생성자의 파라미터 타입을 가져옵니다.
                Class<?>[] pType = ctor.getParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    // 5. 파라미터 타입 중 하나가 cArg와 일치하면 해당 생성자를 출력합니다.
                    if (pType[i].equals(cArg)) {
                        out.format("%s%n", ctor.toGenericString());

                        // 6. 해당 생성자의 제네릭 파라미터 타입을 가져와서 출력합니다.
                        Type[] gpType = ctor.getGenericParameterTypes();
                        for (int j = 0; j < gpType.length; j++) {
                            // 7. cArg와 일치하는 파라미터 타입에 '*' 기호를 추가합니다.
                            char ch = (pType[j].equals(cArg) ? '*' : ' ');
                            out.format("%7c%s[%d]: %s%n", ch,
                                       "GenericParameterType", j, gpType[j]);
                        }
                        break; // 일치하는 파라미터를 찾았으므로 현재 생성자에 대한 처리를 마칩니다.
                    }
                }
            }

            // 실제 코드에서는 이러한 예외를 더 우아하게 처리해야 합니다.
        } catch (ClassNotFoundException x) {
            x.printStackTrace(); // 클래스가 발견되지 않았을 때 예외 처리
        }
    }
}