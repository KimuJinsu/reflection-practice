package com.jinsu.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RestoreAliases {

    // Email 별칭을 저장하기 위한 기본 Map 정의
    private static Map<String, String> defaultAliases = new HashMap<String, String>();
    
    // 정적 블록을 통해 초기값 설정
    static {
        defaultAliases.put("Duke", "duke@i-love-java");
        defaultAliases.put("Fang", "fang@evil-jealous-twin");
    }

    public static void main(String... args) {
        try {
            // EmailAliases 클래스의 HashMap을 파라미터로 받는 생성자를 가져옴
            Constructor ctor = EmailAliases.class.getDeclaredConstructor(HashMap.class);
            
            // 생성자의 접근 제한을 해제하여 private 생성자도 호출 가능하도록 설정
            ctor.setAccessible(true);
            
            // 해당 생성자를 사용해 EmailAliases 객체를 생성, 생성시 defaultAliases 전달
            EmailAliases email = (EmailAliases) ctor.newInstance(defaultAliases);
            
            // 생성된 EmailAliases 객체의 printKeys() 메서드를 호출하여 키 출력
            email.printKeys();

        // 다양한 예외를 캐치하여 발생할 수 있는 오류에 대해 처리
        } catch (InstantiationException x) {
            x.printStackTrace();  // 객체 생성 중 발생하는 예외 처리
        } catch (IllegalAccessException x) {
            x.printStackTrace();  // 접근 제한 예외 처리
        } catch (InvocationTargetException x) {
            x.printStackTrace();  // 생성자 실행 중 발생한 예외 처리
        } catch (NoSuchMethodException x) {
            x.printStackTrace();  // 생성자를 찾을 수 없을 때 예외 처리
        }
    }
}