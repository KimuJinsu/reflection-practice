package com.jinsu.reflectionapi;

import java.io.Console;
import java.nio.charset.Charset;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import static java.lang.System.out;

public class ConsoleCharset {
    public static void main(String... args) {
        // Console 클래스의 선언된 모든 생성자를 가져옴
        Constructor[] ctors = Console.class.getDeclaredConstructors();
        Constructor ctor = null;
        
        // 생성자 배열을 순회하며, 인자가 없는 생성자를 찾음
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;  // 인자가 없는 생성자를 찾으면 반복문 종료
        }

        try {
            // 접근 제한자인 생성자(예: private)를 접근 가능하도록 설정
            ctor.setAccessible(true);
            
            // reflection을 사용하여 Console 객체를 생성
            Console c = (Console) ctor.newInstance();
            
            // Console 클래스의 private 필드인 'cs'를 가져옴
            Field f = c.getClass().getDeclaredField("cs");
            
            // 해당 필드를 접근 가능하도록 설정
            f.setAccessible(true);
            
            // Console 객체에서 'cs' 필드 값을 가져와 출력
            out.format("Console charset         :  %s%n", f.get(c));
            
            // 기본 Charset을 가져와 출력
            out.format("Charset.defaultCharset():  %s%n", Charset.defaultCharset());

        // 예외 처리를 통해 발생할 수 있는 다양한 예외들을 잡아냄
        } catch (InstantiationException x) {
            x.printStackTrace();  // 객체 생성 실패 예외 처리
        } catch (InvocationTargetException x) {
            x.printStackTrace();  // 생성자 호출 중 발생한 예외 처리
        } catch (IllegalAccessException x) {
            x.printStackTrace();  // 접근 제어 위반 예외 처리
        } catch (NoSuchFieldException x) {
            x.printStackTrace();  // 필드를 찾을 수 없을 때 예외 처리
        } 
    }
}