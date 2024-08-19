package com.jinsu.reflectionapi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokeMain {
    public static void main(String... args) {
        try {
       	/*  실행시킬 클래스 이 : com.jinsu.reflectionapi.InvokeMain 
			args[0] :com.jinsu.reflectionapi.Deet 
			args[1] :com.jinsu.reflectionapi.Deet
			ja 
			JP 
			JP      */
        
            // 1. 명령줄 인자로 전달된 클래스 이름으로 클래스를 로드합니다.
            Class<?> c = Class.forName(args[0]);

            // 2. main 메서드의 파라미터 타입을 설정합니다. main 메서드는 String[]을 받습니다.
            Class[] argTypes = new Class[] { String[].class };

            // 3. 로드된 클래스에서 main 메서드를 가져옵니다.
            Method main = c.getDeclaredMethod("main", argTypes);

            // 4. 나머지 인자를 String[]로 변환합니다. 이는 main 메서드에 전달할 인수입니다.
            String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);

            // 5. 형식화된 문자열을 출력합니다. 클래스 이름과 함께 main 메서드를 호출한다고 표시합니다.
            System.out.format("invoking %s.main()%n", c.getName());

            // 6. main 메서드를 호출합니다. main 메서드는 static 메서드이므로 첫 번째 인자는 null입니다.
            main.invoke(null, (Object)mainArgs);

            // 7. 예외 처리: 실제 코드에서는 예외를 더 우아하게 처리해야 합니다.
        } catch (ClassNotFoundException x) {
            x.printStackTrace(); // 클래스가 발견되지 않았을 때 예외 처리
        } catch (NoSuchMethodException x) {
            x.printStackTrace(); // 메서드가 발견되지 않았을 때 예외 처리
        } catch (IllegalAccessException x) {
            x.printStackTrace(); // 메서드 접근 권한이 없을 때 예외 처리
        } catch (InvocationTargetException x) {
            x.printStackTrace(); // 메서드 호출 중 발생한 예외 처리
        }
    }
}