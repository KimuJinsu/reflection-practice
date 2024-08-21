package com.jinsu.dynamicproxy3;

public class AProxy implements AInterface {
    AInterface subject;

    AProxy(AInterface subject) {
        this.subject = subject;
    }

    @Override
    public String call() {
        System.out.println("TimeProxy 실행");
        long startTime = System.nanoTime();

        String result = subject.call();

        long endTime = System.nanoTime();
        long resultTime = endTime - startTime;
        System.out.println("TimeProxy 종료 resultTime = " + resultTime);

        return result;
    }
}
