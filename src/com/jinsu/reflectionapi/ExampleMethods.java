package com.jinsu.reflectionapi;

import java.util.Collection;
import java.util.List;

public class ExampleMethods<T> {
    
    // 이 클래스는 제네릭 타입 T를 사용합니다.
    // 현재는 필드와 생성자가 주석 처리되어 있습니다.

    // 간단한 메서드, 문자열과 정수를 인자로 받고 boolean을 반환합니다.
    public boolean simpleMethod(String stringParam, int intParam) {
        System.out.println("String: " + stringParam + ", integer: " + intParam); 
        return true;
    }
    
    // 가변 인자 메서드, 여러 문자열을 인자로 받고 문자열의 개수를 반환합니다.
    public int varArgsMethod(String... manyStrings) {
        return manyStrings.length;
    }
    
    // List를 인자로 받아 리스트가 비어 있는지 여부를 boolean으로 반환합니다.
    public boolean methodWithList(List<String> listParam) {
        return listParam.isEmpty();
    }
    
    // 제네릭 메서드, 제네릭 배열과 컬렉션을 인자로 받아 각각의 크기를 출력합니다.
    public <T> void genericMethod(T[] a, Collection<T> c) {
        System.out.println("Length of array: " + a.length);
        System.out.println("Size of collection: " + c.size()); 
    }
}