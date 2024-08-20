package com.jinsu.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;

class EmailAliases {
    // private 필드로 이메일 별칭들의 키를 저장하는 Set
    private Set<String> aliases;

    // private 생성자, HashMap<String, String>을 인자로 받아 aliases에 키값들을 저장
    private EmailAliases(HashMap<String, String> h) {
        aliases = h.keySet(); // HashMap에서 키 값만 추출하여 Set에 저장
    }

    // aliases에 저장된 키 값을 출력하는 메서드
    public void printKeys() {
        out.format("Mail keys:%n");
        for (String k : aliases) // Set에 저장된 각 키를 순회하며 출력
            out.format("  %s%n", k);
    }
}