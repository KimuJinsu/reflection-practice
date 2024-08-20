package com.jinsu.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Human {

    private String name;
    public int number;
    protected boolean tr;

    // 매개변수 있는 생성자
    public Human(String name){
        this.name = name;
    }

    // 매개변수 없는 기본 생성자 (private)
    private Human(){
    }

    public void goRestRoom(){
        System.out.println(name + "이 화장실로 갑니다.");
    }

    public void offPants(){
        System.out.println(name + "이 바지를 내립니다.");
    }

    public void doWork(){
        System.out.println(name + "이 볼일을 봅니다.");
        poopOut();
    }

    private void poopOut(){
        System.out.println("똥이 나왔습니다.");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
    	// JVM에 있는 클래스 정보 가져오기
    	Class<?> class1 = Class.forName("com.jinsu.reflectionapi.Human");

    	// 리플렉션을 통해 생성자 가져오기
    	Constructor<?> constructor1 = class1.getDeclaredConstructor();
    	Constructor<?> constructor2 = class1.getDeclaredConstructor(String.class);

    	constructor1.setAccessible(true); // 해당 생성자에 접근할 수 있도록 설정

    	// 가져온 생성자를 통해 객체 생성하기
    	Object human1 = constructor1.newInstance();
    	Object human2 = constructor2.newInstance("승갱이");
    }
}