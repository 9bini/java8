package com.koobin.lambda;

@FunctionalInterface
public interface RunSomething {

    // abstract 생략되어 있다는 걸 기억하자.
    void doIt();

    /**
     * 아래 코드르 추가할 시 SAM 형태를 위반하여 @FunctionalInterface에서 컴파일 에러를 추력한다.
     *
     * void doAgain();
     */
    static void printName(){
        System.out.println("Taekyun");
    }

    // default는 java 8에서 새로 추가된 기능
    default void printAge(){
        System.out.println("30");
    }
}
