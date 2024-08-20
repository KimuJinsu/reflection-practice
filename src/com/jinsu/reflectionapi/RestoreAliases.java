package com.jinsu.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;

// EmailAliases 클래스 정의
class EmailAliases {
    // Set<String> 타입의 private 필드
    private Set<String> aliases;

    // private 생성자, HashMap<String, String>을 받아 aliases 필드 초기화
    private EmailAliases(HashMap<String, String> h) {
        aliases = h.keySet(); // HashMap의 키를 Set으로 저장
    }

    // aliases에 저장된 키를 출력하는 메서드
    public void printKeys() {
        out.format("Mail keys:%n");
        for (String k : aliases) // aliases의 각 키를 출력
            out.format("  %s%n", k);
    }
}

// RestoreAliases 클래스 정의
public class RestoreAliases {

    // 기본 이메일 별칭을 저장할 static 필드
    private static Map<String, String> defaultAliases = new HashMap<String, String>();
    
    // static 블록을 통해 defaultAliases에 초기값 설정
    static {
        defaultAliases.put("Duke", "duke@i-love-java");
        defaultAliases.put("Fang", "fang@evil-jealous-twin");
    }

    // main 메서드
    public static void main(String... args) {
        try {
            // EmailAliases 클래스의 private 생성자를 가져오기 위해 getDeclaredConstructor 사용
            Constructor<?> ctor = EmailAliases.class.getDeclaredConstructor(HashMap.class);
            
            // 생성자의 접근 제한을 해제
            ctor.setAccessible(true);
            
            // 생성자를 사용하여 EmailAliases 객체를 생성
            EmailAliases email = (EmailAliases) ctor.newInstance(defaultAliases);
            
            // 생성된 객체의 메서드를 호출하여 이메일 별칭의 키를 출력
            email.printKeys();

        // 예외 처리
        } catch (InstantiationException x) {
            x.printStackTrace(); // 객체 생성 예외
        } catch (IllegalAccessException x) {
            x.printStackTrace(); // 접근 제어 예외
        } catch (InvocationTargetException x) {
            x.printStackTrace(); // 생성자 호출 중 예외
        } catch (NoSuchMethodException x) {
            x.printStackTrace(); // 생성자 찾기 예외
        }
    }
}